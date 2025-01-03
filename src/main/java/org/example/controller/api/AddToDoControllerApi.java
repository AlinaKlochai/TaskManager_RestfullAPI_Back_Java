package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.validationErrorDto.ValidationErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AddToDoControllerApi {

    /**
     * Controller for adding a new ToDo.
     * Handles POST requests to create new tasks.
     * Returns 201 Created if the operation is successful.
     *
     * @param requestDTO DTO with task description and due date.
     * @return ResponseEntity with the created task (ToDoResponseDTO).
     */
    @Operation(summary = "Add new todo with due date", description = "The operation is available for user to add a new to do with due date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Todo was created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToDoCreateRequestDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDto.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<?> addNewToDo(@Valid @RequestBody ToDoCreateRequestDTO requestDTO);
}
