package br.com.lulira.services;


import br.com.lulira.data.dto.v1.PersonDTO;
import br.com.lulira.data.dto.v2.PersonDTOV2;
import br.com.lulira.exception.ResourceNotFoundException;
import static br.com.lulira.mapper.ObjectMapper.parseListObjects;
import static br.com.lulira.mapper.ObjectMapper.parseObject;

import br.com.lulira.mapper.custom.PersonMapper;
import br.com.lulira.model.Person;
import br.com.lulira.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();

    //Logger do lsf4j
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;


    public List<PersonDTO> findAll() {

        logger.info("finding all People!");

        return parseListObjects(repository.findAll(), PersonDTO.class);

    }

        public PersonDTO findById(Long id) {

        logger.info("finding one person!");

        var entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person");

        var entity =  parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating one Person");

        var entity =  converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("updating one Person");
        Person entity =  repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));


        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){

        logger.info("deleting one Person");

        Person entity =  repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);

    }


}
