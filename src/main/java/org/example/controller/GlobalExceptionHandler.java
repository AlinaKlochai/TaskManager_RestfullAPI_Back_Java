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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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

        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorsDto> handleValidationException(MethodArgumentNotValidException e) {

            List<ValidationErrorDto> validationErrors = new ArrayList<>();

            List<ObjectError> errors = e.getBindingResult().getAllErrors();

            // Собираем ошибки в соответствующий формат
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError)error;
                ValidationErrorDto errorDto = ValidationErrorDto.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build();

                validationErrors.add(errorDto);
            }


            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorsDto.builder()
                            .errors(validationErrors)
                            .build());
        }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLockException(ObjectOptimisticLockingFailureException ex) {
        return new ResponseEntity<>("Conflict occurred: The record was modified by another transaction.", HttpStatus.CONFLICT);
    }

}
