package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public ToDoResponseDTO addToDo(ToDoCreateRequestDTO toDoCreateRequestDTO) {

        ToDo newToDo = toDoMapper.toModel(toDoCreateRequestDTO);

        ToDo savedToDo = toDoRepository.save(newToDo);

        return toDoMapper.toResponseDTO(savedToDo);
    }
}
