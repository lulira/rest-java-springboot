package br.com.lulira.controllers;


import br.com.lulira.controllers.docs.PersonControllerDocs;
import br.com.lulira.data.dto.v1.PersonDTO;
import br.com.lulira.data.dto.v2.PersonDTOV2;
import br.com.lulira.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Find All People",
            description = "Finds All People",
            tags = {"People"}, responses = {@ApiResponse
            (description = "Success",
                    responseCode = "204",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))

                    )

                    }

            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    @Override
    public List<PersonDTO> findAll() {
        return service.findAll();

    }

    @GetMapping(value = "/{id}",


            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}

    )
    @Operation(summary = "Finds a person",
            description = "Find a specific person by your ID",
            tags = {"People"}, responses = {@ApiResponse
            (description = "Success",
                    responseCode = "204",
                    content = @Content(schema = @Schema(implementation = PersonDTO.class))

            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {
    return service.findById(id);

}

@PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Operation(summary = "Adds a new Person",
        description = "Adds a new person by passing in a JSON, XML or YML representation of the person.",
        tags = {"People"},
        responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = PersonDTO.class))
                ),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
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
@Operation(summary = "Updates a person's information",
        description = "Updates a person's information by passing in a JSON, XML or YML representation of the updated person.",
        tags = {"People"},
        responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = PersonDTO.class))
                ),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
)
@Override
public PersonDTO update(@RequestBody PersonDTO person) {
    return service.update(person);

}

    @Operation(summary = "Deletes a Person",
            description = "Deletes a specific person by their ID",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();

}
}
