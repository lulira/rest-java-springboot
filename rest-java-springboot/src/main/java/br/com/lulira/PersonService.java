package br.com.lulira;


import br.com.lulira.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {

        logger.info("finding all People!");

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {

            Person person = mockPerson(i);
            persons.add(person);


        }
        return persons;
    }


    public Person findById(String id) {

        logger.info("finding one person!");

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Luciano");
        person.setLastName("Lira");
        person.setAddress("Parnamirim - Rio Grande do Norte - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one Person");
        return person;
    }

    public Person update(Person person) {
        logger.info("updating one Person");
        return person;
    }

    public void delete(String id){

        logger.info("deleting one Person");
    }

    private Person mockPerson(int i) {

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("firstName" + i);
        person.setLastName("lastName" + i);
        person.setAddress("Some address in Brasil");
        person.setGender("Male");
        return person;

    }
}
