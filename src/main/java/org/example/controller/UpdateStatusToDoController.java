package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.DTO.appDTO.OneMessageDTO;
import org.example.service.UpdateToDoStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UpdateStatusToDoController {

    private final UpdateToDoStatusService updateToDoStatusService;

    @PutMapping("todo/{id}")
    public ResponseEntity<?> updateStatusToDo(@PathVariable Long id) {
        Object resultFromUpdate = updateToDoStatusService.updateStatus(id);

        if(resultFromUpdate instanceof OneMessageDTO) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body((OneMessageDTO) resultFromUpdate);
        }

        return ResponseEntity.ok(resultFromUpdate);
    }
}
