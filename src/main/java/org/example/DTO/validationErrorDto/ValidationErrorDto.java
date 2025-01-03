package org.example.DTO.validationErrorDto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(description = "DTO for representing validation errors in API responses")
public class ValidationErrorDto {

    @Schema(
            description = "The name of the field where the validation error occurred",
            example = "description"
    )
    private String field;

    @Schema(
            description = "The validation error message associated with the field",
            example = "Description must be between 5 and 255 characters"
    )
    private String message;

    public ValidationErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
