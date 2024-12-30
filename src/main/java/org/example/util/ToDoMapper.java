package org.example.util;

import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.ToDo;
import org.example.model.State;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ToDoMapper {

    // Метод для преобразования ToDoCreateRequestDTO в ToDo
    public ToDo toModel(ToDoCreateRequestDTO toDoCreateRequestDTO) {

        ToDo toDo = new ToDo();
        toDo.setDescription(toDoCreateRequestDTO.getDescription());
        toDo.setDueDate(toDoCreateRequestDTO.getDueDate()); // Устанавливаем текущую дату
        toDo.setState(State.OPEN);  // Устанавливаем начальное состояние

        return toDo;
    }

    // Метод для преобразования ToDo в ToDoResponseDTO
    public ToDoResponseDTO toResponseDTO(ToDo toDo) {
        ToDoResponseDTO toDoResponseDTO = new ToDoResponseDTO();
        toDoResponseDTO.setId(toDo.getId());
        toDoResponseDTO.setDescription(toDo.getDescription());
        toDoResponseDTO.setDueDate(toDo.getDueDate());
        toDoResponseDTO.setState(toDo.getState());

        return toDoResponseDTO;
    }

    // Метод для преобразования ToDoResponseDTO в ToDo
    public ToDo fromResponseToModel(ToDoResponseDTO toDoResponseDTO) {
        if (toDoResponseDTO == null) {
            return null;
        }

        ToDo toDo = new ToDo();
        toDo.setId(toDoResponseDTO.getId());
        toDo.setDescription(toDoResponseDTO.getDescription());
        toDo.setDueDate(toDoResponseDTO.getDueDate());
        toDo.setState(toDoResponseDTO.getState());

        return toDo;
    }
}
