package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.DTO.ToDoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API interface for updating the status of a ToDo task.
 */
@RestController
public interface UpdateStatusToDoControllerApi {

    /**
     * Updates the status of a ToDo task by its ID.
     *
     * @param id The ID of the ToDo task to be updated
     * @return ResponseEntity with the updated task or an error message
     */
    @Operation(
            summary = "Update the status of a ToDo task",
            description = "Changes the status of a ToDo task by its ID. Only allows changes from OPEN to DONE."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated the ToDo task",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToDoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict: Status change not allowed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema( example = "{\"message\": \"Status change not allowed. Can only change from IN_PROGRESS to DONE.\"}" ))

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"An unexpected error occurred\"}"))
            )
    })
    @PutMapping("todo/{id}")
    ResponseEntity<?> updateStatusToDo(@PathVariable Long id);
}
