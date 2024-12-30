package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.DTO.errorDto.ErrorResponseDto;
import org.example.DTO.errorDto.FieldErrorDto;
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
    public ResponseEntity<?> addNewToDo(@Valid @RequestBody ToDoCreateRequestDTO requestDTO, BindingResult result) {

        if (result.hasErrors()) {
            // Собираем все ошибки с полей
            List<FieldErrorDto> fieldErrors = result.getFieldErrors().stream()
                    // Собираем ошибки, исключая дубликаты по полям
                    .collect(Collectors.groupingBy(fieldError -> fieldError.getField()))
                    .entrySet().stream()
                    .map(entry -> {
                        // Возвращаем только одну ошибку на поле, например, первую по порядку
                        String message = entry.getValue().get(0).getDefaultMessage();
                        return new FieldErrorDto(entry.getKey(), message);
                    })
                    .collect(Collectors.toList());

            // Формируем ответ с ошибками
            ErrorResponseDto errorResponse = new ErrorResponseDto("Validation failed", fieldErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }


        ToDoResponseDTO responseDTO = addToDoService.addToDo(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
