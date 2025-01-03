package org.example.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface DeleteToDoControllerApi {

    /**
     * Controller for deleting current ToDo by its ID.
     *
     * @param id the ID of the ToDo task to be deleted
     * @return ResponseEntity with HTTP 204 No Content status if successful
     */

    @Operation(summary = "Deletes a ToDo task by its ID", description = "Removes a ToDo task from the database by the provided ID. Returns 204 if successful.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "ToDo was successfully deleted",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo with the specified ID was not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"ToDo with ID 5 not found\"}"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"An unexpected error occurred\"}"))
            )
    })
    @DeleteMapping("todo/{id}")
    ResponseEntity<Void> deleteToDo(@PathVariable Long id);
}
