package org.example.util;

import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.ToDo;
import org.example.model.State;
import org.springframework.stereotype.Component;


@Component
public class ToDoMapper {

    // ToDoCreateRequestDTO to ToDo
    public ToDo toModel(ToDoCreateRequestDTO toDoCreateRequestDTO) {

        ToDo toDo = new ToDo();
        toDo.setDescription(toDoCreateRequestDTO.getDescription());
        toDo.setDueDate(toDoCreateRequestDTO.getDueDate());
        toDo.setState(State.OPEN);

        return toDo;
    }

    //  ToDo to ToDoResponseDTO
    public ToDoResponseDTO toResponseDTO(ToDo toDo) {
        ToDoResponseDTO toDoResponseDTO = new ToDoResponseDTO();
        toDoResponseDTO.setId(toDo.getId());
        toDoResponseDTO.setDescription(toDo.getDescription());
        toDoResponseDTO.setDueDate(toDo.getDueDate());
        toDoResponseDTO.setState(toDo.getState());

        return toDoResponseDTO;
    }

    // ToDoResponseDTO to ToDo
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
