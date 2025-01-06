package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class AddToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    public void cleanUp(){
        toDoRepository.deleteAll();
    }


    @Test
    void testAddNewToDo_ShouldReturnCreatedToDoDTOWithStatus() throws Exception {

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO("Test description", new Date());

        mockMvc.perform(post("/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoCreateRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.description").value("Test description"))
                .andExpect(jsonPath("$.state").value("OPEN"));
    }

    @Test
    void testAddNewToDo_ShouldReturnBadRequestWhenDataIsInvalid() throws Exception {
        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO("", null);

        mockMvc.perform(post("/todo/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(toDoCreateRequestDTO)))
                .andExpect(status().isBadRequest());
    }
}
