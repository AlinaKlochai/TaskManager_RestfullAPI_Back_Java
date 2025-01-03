package org.example.servise;

import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.State;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.service.AddToDoService;
import org.example.util.ToDoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddToDoServiceTest {

    @InjectMocks
    private AddToDoService addToDoService;

    @Mock
    private ToDoMapper toDoMapper;
    @Mock
    private ToDoRepository toDoRepository;

    private ToDoCreateRequestDTO toDoCreateRequestDTO;
    private ToDo toDo;
    private ToDoResponseDTO responseDTO;

    @BeforeEach
    void setUp(){
        toDoCreateRequestDTO = new ToDoCreateRequestDTO("Test description", new Date());
        toDo = new ToDo();
        toDo.setDescription("Test description");
        toDo.setState(State.OPEN);

        responseDTO = new ToDoResponseDTO(1l, "Test description", new Date(), State.OPEN);
    }

    @Test
    void testAddToDo_ShouldReturnToDoResponseDTO(){
        when(toDoMapper.toModel(toDoCreateRequestDTO)).thenReturn(toDo);
        when(toDoRepository.save(toDo)).thenReturn(toDo);
        when(toDoMapper.toResponseDTO(toDo)).thenReturn(responseDTO);

        ToDoResponseDTO resultResponseDTO = addToDoService.addToDo(toDoCreateRequestDTO);

        assertNotNull(resultResponseDTO);
        assertEquals("Test description", resultResponseDTO.getDescription());
        assertEquals(1L, resultResponseDTO.getId());
        assertEquals(State.OPEN, resultResponseDTO.getState());

        verify(toDoMapper).toModel(toDoCreateRequestDTO);
        verify(toDoRepository).save(toDo);
        verify(toDoMapper).toResponseDTO(toDo);
    }
}
