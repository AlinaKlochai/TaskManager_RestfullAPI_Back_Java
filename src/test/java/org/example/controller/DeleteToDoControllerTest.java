package org.example.controller;

import org.example.model.State;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class DeleteToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepository toDoRepository;

    private ToDo testToDo;

    @BeforeEach
    public void setUp() throws Exception {
        testToDo = new ToDo();
        testToDo.setDescription("Test description");
        testToDo.setState(State.OPEN);
        testToDo.setDueDate(new Date());

        testToDo = toDoRepository.save(testToDo);
    }

    @AfterEach
    public void cleanUp(){
        toDoRepository.deleteAll();
    }

    @Test
    void testDeleteToDo_ShouldReturnNoContent() throws Exception {

        mockMvc.perform(delete("/todo/{id}", testToDo.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(toDoRepository.findById(testToDo.getId())).isEmpty();
    }

    @Test
    void testDeleteToDo_ShouldReturnNotFoundException() throws Exception {

        mockMvc.perform(delete("/todo/{id}", 54L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
