package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.DTO.validationErrorDto.ValidationErrorDto;
import org.example.DTO.validationErrorDto.ValidationErrorsDto;
import org.example.service.AddToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AddToDoController {

    private final AddToDoService addToDoService;

    @PostMapping("todo/")
    public ResponseEntity<?> addNewToDo(@Valid @RequestBody ToDoCreateRequestDTO requestDTO) {
        ToDoResponseDTO responseDTO = addToDoService.addToDo(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }



}
