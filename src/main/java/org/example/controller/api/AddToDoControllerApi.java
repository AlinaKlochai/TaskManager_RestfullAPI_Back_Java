package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.DTO.ToDoCreateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AddToDoControllerApi {

    @Operation(summary = "Add new todo with due date", description = "The operation is available for user to add a new to do with due date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Todo was created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToDoCreateRequestDTO.class)))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<?> addNewToDo(@Valid @RequestBody ToDoCreateRequestDTO requestDTO);
}
