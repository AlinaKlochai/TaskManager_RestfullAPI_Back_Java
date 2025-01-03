package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.controller.api.AddToDoControllerApi;
import org.example.service.AddToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AddToDoController implements AddToDoControllerApi {

    private final AddToDoService addToDoService;

    @PostMapping("todo/")
    public ResponseEntity<?> addNewToDo(@Valid @RequestBody ToDoCreateRequestDTO requestDTO) {
        ToDoResponseDTO responseDTO = addToDoService.addToDo(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

}
