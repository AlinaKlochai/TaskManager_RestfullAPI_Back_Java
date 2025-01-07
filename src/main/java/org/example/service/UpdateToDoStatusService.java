package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.appDTO.OneMessageDTO;
import org.example.model.State;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UpdateToDoStatusService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;
    private final FindToDoService findToDoService;

    public Object updateStatus(Long toDoId) {
        ToDo toDo = toDoMapper.fromResponseToModel(findToDoService.findToDosByToDoId(toDoId));

        if (toDo.getState() != State.OPEN) {
            return new OneMessageDTO("Status change not allowed. Can only change from IN_PROGRESS to DONE.");
        }

        // set new status
        toDo.setState(State.DONE);
        ToDo updatedToDo = toDoRepository.save(toDo);

        return toDoMapper.toResponseDTO(updatedToDo);
    }
}
