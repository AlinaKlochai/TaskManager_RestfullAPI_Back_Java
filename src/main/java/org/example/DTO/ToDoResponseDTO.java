package org.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.State;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoResponseDTO {

    @Schema(description = "Id of current toDo", example = "1")
    private Long id;

    @Schema(description = "ToDo description", example = "To make one project")
    private String description;

    @Schema(description = "Due Date", example = "2024-12-31")
    private Date dueDate;

    @Schema(description = "State of ToDo", example = "IN_PROGRESS")
    private State state;
}
