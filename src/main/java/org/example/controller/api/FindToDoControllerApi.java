package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.DTO.ToDoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API interface for retrieving all ToDo tasks.
 */
@RestController
public interface FindToDoControllerApi {

    /**
     * Retrieves all ToDo tasks from the database.
     *
     * @return ResponseEntity containing a list of ToDoResponseDTO with HTTP 200 OK status if successful
     */

    @Operation(
            summary = "Retrieve all ToDo tasks",
            description = "Fetches all ToDo tasks from the database and returns them as a list. If no tasks are available, an empty list is returned."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all ToDo tasks",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToDoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"An unexpected error occurred\"}"))
            )
    })
    @GetMapping("todos/")
    ResponseEntity<List<ToDoResponseDTO>> findToDo();
}
