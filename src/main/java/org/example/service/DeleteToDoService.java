package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteToDoService {

    private final ToDoRepository toDoRepository;
    private final FindToDoService findToDoService;
    private final ToDoMapper toDoMapper;

    public void deleteToDo(Long toDoId) {
        ToDo foundetToDo = toDoMapper.fromResponseToModel(findToDoService.findToDosByToDoId(toDoId));

        toDoRepository.deleteById(toDoId);
    }
}
