package org.example.service;

import lombok.AllArgsConstructor;
import org.example.repository.ToDoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteToDoService {

    private final ToDoRepository toDoRepository;
    private final FindToDoService findToDoService;

    public void deleteToDo(Long toDoId) {
        findToDoService.findToDosByToDoId(toDoId);

        toDoRepository.deleteById(toDoId);
    }
}
