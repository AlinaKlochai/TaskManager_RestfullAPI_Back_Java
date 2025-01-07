package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.controller.api.DeleteToDoControllerApi;
import org.example.service.DeleteToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DeleteToDoController implements DeleteToDoControllerApi {

    private final DeleteToDoService deleteToDoService;

    @DeleteMapping("todo/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        deleteToDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}
