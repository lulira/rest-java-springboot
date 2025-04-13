package br.com.lulira;


import br.com.lulira.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

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
}
