package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.ToDoResponseDTO;
import org.example.exception.NotFoundException;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindToDoService {
    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public List<ToDoResponseDTO> findAllToDosSortedByDateDesc() {
        return toDoRepository.findAll(Sort.by(Sort.Order.asc("dueDate")))
                .stream()
                .map(toDoMapper::toResponseDTO)
                .toList();
    }

    public ToDoResponseDTO findToDosByToDoId(Long toDoId) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(toDoId);

        return toDoOptional.map(toDoMapper::toResponseDTO)
                .orElseThrow(() -> new NotFoundException("ToDo with ID " + toDoId + " not found"));
    }
}
