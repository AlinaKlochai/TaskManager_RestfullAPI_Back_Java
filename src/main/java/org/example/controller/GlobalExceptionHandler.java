package org.example.controller;

import org.example.DTO.errorDto.ErrorResponseDto;
import org.example.DTO.validationErrorDto.ValidationErrorDto;
import org.example.DTO.validationErrorDto.ValidationErrorsDto;
import org.example.exception.AlreadyExistException;
import org.example.exception.NotFoundException;
import org.example.exception.NullArgException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handlerNotFoundException(NotFoundException exception){
            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message(exception.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(AlreadyExistException.class)
        public ResponseEntity<ErrorResponseDto> handlerAlreadyExistException(AlreadyExistException exception){
            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message(exception.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }


        @ExceptionHandler(NullPointerException.class)
        public ResponseEntity<ErrorResponseDto> handlerNullPointerException(NullPointerException exception){
            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message(exception.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(NullArgException.class)
        public ResponseEntity<String> handlerNullArgException(NullArgException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationErrorDto> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(fieldError -> fieldError.getField()))
                .entrySet().stream()
                .map(entry -> {
                    String field = entry.getKey();
                    List<FieldError> fieldErrors = entry.getValue();

                    // Собираем ошибки для конкретного поля
                    List<String> messages = fieldErrors.stream()
                            .map(FieldError::getDefaultMessage)
                            .distinct() // Исключаем дублирующиеся ошибки
                            .collect(Collectors.toList());

                    // Создаём ValidationErrorDto для каждого поля с его ошибками
                    return ValidationErrorDto.builder()
                            .field(field)
                            .message(String.join(", ", messages))
                            .build();
                })
                .collect(Collectors.toList());

        // Если есть ошибки, возвращаем их
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(ValidationErrorsDto.builder()
                    .errors(validationErrors)
                    .build());
        }

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLockException(ObjectOptimisticLockingFailureException ex) {
        return new ResponseEntity<>("Conflict occurred: The record was modified by another transaction.", HttpStatus.CONFLICT);
    }

}
