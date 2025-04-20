package br.com.lulira.services;


import br.com.lulira.controllers.PersonController;
import br.com.lulira.data.dto.v1.PersonDTO;
import br.com.lulira.data.dto.v2.PersonDTOV2;
import br.com.lulira.exception.ResourceNotFoundException;

import static br.com.lulira.mapper.ObjectMapper.parseListObjects;
import static br.com.lulira.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
persons.forEach(this::addHateoasLinks);
        return persons;

    }

    public PersonDTO findById(Long id) {

        logger.info("finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person");

        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating one Person");

        var entity = converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("updating one Person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));


        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("deleting one Person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);

    }


    // criando um metodo para implementar hateoas para todos os verbos http
    private  void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));


        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
