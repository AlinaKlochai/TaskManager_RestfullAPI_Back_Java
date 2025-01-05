package org.example;

import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.State;
import org.example.model.ToDo;
import org.example.util.ToDoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToDoMapperTest {

    private ToDoMapper toDoMapper;

    @BeforeEach
    void setUp(){
        toDoMapper = new ToDoMapper();
    }

    @Test
    void testToModel(){

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO();

        toDoCreateRequestDTO.setDescription("ToDo 1");
        toDoCreateRequestDTO.setDueDate(new Date());

        ToDo toDo = toDoMapper.toModel(toDoCreateRequestDTO);

        assertNotNull(toDo);
        assertEquals(toDoCreateRequestDTO.getDescription(), toDo.getDescription());
        assertEquals(toDoCreateRequestDTO.getDueDate(), toDo.getDueDate());
        assertEquals(State.OPEN, toDo.getState());
    }

    @Test
    void testToDoToResponseDto(){
         ToDo toDo = new ToDo();
         toDo.setDescription("ToDo 1");
         toDo.setDueDate(new Date());
         toDo.setState(State.OPEN);
         toDo.setId(1L);

         ToDoResponseDTO toDoResponseDTO = toDoMapper.toResponseDTO(toDo);

         assertNotNull(toDoResponseDTO);
         assertEquals(toDo.getDescription(), toDoResponseDTO.getDescription());
         assertEquals(toDo.getDueDate(), toDoResponseDTO.getDueDate());
         assertEquals(State.OPEN, toDo.getState());
         assertEquals(1L, toDoResponseDTO.getId());
    }

    @Test
    void testFromResponseToModel(){
        ToDoResponseDTO toDoResponseDTO = new ToDoResponseDTO();
        toDoResponseDTO.setDescription("ToDo 1");
        toDoResponseDTO.setDueDate(new Date());
        toDoResponseDTO.setState(State.OPEN);
        toDoResponseDTO.setId(1L);

        ToDo toDo = toDoMapper.fromResponseToModel(toDoResponseDTO);

        assertNotNull(toDo);
        assertEquals(toDoResponseDTO.getDescription(), toDo.getDescription());
        assertEquals(toDoResponseDTO.getDueDate(), toDo.getDueDate());
        assertEquals(State.OPEN, toDo.getState());
        assertEquals(1L, toDoResponseDTO.getId());
    }
}
