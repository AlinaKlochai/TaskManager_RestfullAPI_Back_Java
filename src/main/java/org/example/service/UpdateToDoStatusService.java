package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.appDTO.OneMessageDTO;
import org.example.model.State;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UpdateToDoStatusService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;
    private final FindToDoService findToDoService;

    public Object updateStatus(Long toDoId) {
        // Получаем ToDo из репозитория
        ToDo toDo = toDoMapper.fromResponseToModel(findToDoService.findToDosByToDoId(toDoId));

        // Проверяем допустимость изменения статуса
        if (toDo.getState() != State.OPEN) {
            return new OneMessageDTO("Status change not allowed. Can only change from IN_PROGRESS to DONE.");
        }

        // Обновляем статус
        toDo.setState(State.DONE);
       // toDo.getDueDate(createdAtDate);
        ToDo updatedToDo = toDoRepository.save(toDo);

        // Возвращаем DTO ответа
        return toDoMapper.toResponseDTO(updatedToDo);
    }
}
