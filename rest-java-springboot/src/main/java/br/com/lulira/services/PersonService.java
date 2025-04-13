package br.com.lulira.services;


import br.com.lulira.exception.ResourceNotFoundException;
import br.com.lulira.model.Person;
import br.com.lulira.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;


    public List<Person> findAll() {

        logger.info("finding all People!");

        return repository.findAll();


        }




    public Person findById(Long id) {

        logger.info("finding one person!");

        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));

    }

    public Person create(Person person) {
        logger.info("Creating one Person");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("updating one Person");
        Person entity =  repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));


        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }

    public void delete(Long id){

        logger.info("deleting one Person");

        Person entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);

    }


}
