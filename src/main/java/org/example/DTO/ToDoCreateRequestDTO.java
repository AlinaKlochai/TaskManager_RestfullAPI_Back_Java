package org.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoCreateRequestDTO {

    @Schema(description = "ToDo description", example = "To make one project")
    @NotBlank(message = "Beschreibung darf nicht leer sein.")
    @Pattern(regexp = "^[\\p{L}0-9 .,!?()'\"-]+$", message = "Beschreibung enthält ungültige Zeichen. Zulässig sind Buchstaben, Zahlen, Leerzeichen und die folgenden Symbole: . , ! ? ( ) ' \" -")
    @Size(
            min = 5,
            max = 255,
            message = "Beschreibung muss zwischen 5 und 255 Zeichen lang sein."
    )
    private String description;

    @Schema(description = "Fälligkeitsdatum", example = "2024-12-31")
    @NotNull(message = "Fälligkeitsdatum muss angegeben werden.")
    private Date dueDate;
}
