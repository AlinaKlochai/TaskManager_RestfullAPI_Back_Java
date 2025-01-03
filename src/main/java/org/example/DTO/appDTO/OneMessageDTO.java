package org.example.DTO.appDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for representing a single message from the server.
 * Can be used to provide error messages, status updates, or any server-side notifications.
 */
@Data
@AllArgsConstructor
@Schema(name = "Message", description = "Any message from server")
public class OneMessageDTO {
    @Schema(
            description = "The content of the message. Possible values include error messages, status changes, or server notifications.",
            example = "ToDo not found"
    )
    String message;
}
