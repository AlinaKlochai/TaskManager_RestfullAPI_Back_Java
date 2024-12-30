package org.example.DTO.appDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Message", description = "Any message from server")
public class OneMessageDTO {
    @Schema(description = "Possible: error message, status change, etc.", example = "To do not found")
    String message;
}
