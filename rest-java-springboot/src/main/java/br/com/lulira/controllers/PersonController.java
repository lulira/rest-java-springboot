package br.com.lulira.controllers;


import br.com.lulira.controllers.docs.PersonControllerDocs;
import br.com.lulira.data.dto.v1.PersonDTO;
import br.com.lulira.data.dto.v2.PersonDTOV2;
import br.com.lulira.unittests.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {

    @Autowired
    PersonService service;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })

    @Override
    public List<PersonDTO> findAll() {
        return service.findAll();

    }

    @GetMapping(value = "/{id}",


            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}

    )

    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    @Override
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);

    }

    @PostMapping(value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
        return service.createV2(person);

    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);

    }


    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();

    }
}
