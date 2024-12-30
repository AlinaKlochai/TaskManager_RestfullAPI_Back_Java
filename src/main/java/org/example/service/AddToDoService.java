package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.exception.AlreadyExistException;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public ToDoResponseDTO addToDo(ToDoCreateRequestDTO toDoCreateRequestDTO) {

        ToDo newToDo = toDoMapper.toModel(toDoCreateRequestDTO);

        ToDo savedToDo = toDoRepository.save(newToDo);

        ToDoResponseDTO toDoResponseDTO = toDoMapper.toResponseDTO(savedToDo);

        return toDoResponseDTO;
    }
}
