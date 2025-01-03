package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.DTO.ToDoResponseDTO;
import org.example.controller.api.FindToDoControllerApi;
import org.example.service.FindToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FindToDoController implements FindToDoControllerApi {

    private final FindToDoService findToDoService;

    @GetMapping("todos/")
    public ResponseEntity<List<ToDoResponseDTO>> findToDo() {
        List<ToDoResponseDTO> todos = findToDoService.findAllToDosSortedByDateDesc();
        return ResponseEntity.ok().body(todos);
    }
}
