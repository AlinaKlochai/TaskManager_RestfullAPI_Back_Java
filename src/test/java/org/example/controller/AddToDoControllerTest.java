package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.State;
import org.example.service.AddToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class AddToDoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddToDoService addToDoService;
    @InjectMocks
    private AddToDoController addToDoController;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addToDoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAddNewToDo_ShouldReturnCreatedToDoDTOWithStatus() throws Exception {

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO("Test description", new Date());

        State openState = State.OPEN;

        ToDoResponseDTO toDoResponseDTO = new ToDoResponseDTO(1L, "Test description", new Date(), openState);

        when(addToDoService.addToDo(any(ToDoCreateRequestDTO.class))).thenReturn(toDoResponseDTO);

        mockMvc.perform(post("/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoCreateRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Test description"))
                .andExpect(jsonPath("$.state").value("OPEN"));
    }

    @Test
    void testAddNewToDo_ShouldReturnBadRequestWhenWeSendToDoWithNullInformation() throws Exception {

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO("", null);

        mockMvc.perform(post("/todo/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(toDoCreateRequestDTO)))
                .andExpect(status().isBadRequest());
    }
}
