package com.bounteous.student_service.controller;

import com.bounteous.student_service.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Mina", "mina@example.com", 22);
    }

    @Test
    @DisplayName("POST /students should create a student")
    void testCreateStudent() throws Exception {
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name", is("Mina")))
                        .andExpect(jsonPath("$.email", is("mina@example.com")));
    }


    @Test
    @DisplayName("GET /students should return OK")
    void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /students/{id} should return the student")
    void testGetStudentById() throws Exception {
        String content = objectMapper.writeValueAsString(student);
        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();

        Student createdStudent = objectMapper.readValue(response, Student.class);
        Long id = createdStudent.getId();

        mockMvc.perform(get("/students/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mina"))
                .andExpect(jsonPath("$.email").value("mina@example.com"));
    }

    @Test
    @DisplayName("PUT /students/{id} should update the student")
    void testUpdateStudent() throws Exception {
        String content = objectMapper.writeValueAsString(student);
        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();

        Student created = objectMapper.readValue(response, Student.class);
        created.setName("Updated Name");
        created.setEmail("updated@example.com");


        mockMvc.perform(put("/students/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("Updated Name"))
                        .andExpect(jsonPath("$.email").value("updated@example.com"));

    }

    @Test
    @DisplayName("DELETE /students/{id} should return 204 No Content")
    void testDeleteStudent() throws Exception {
        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                        .andReturn().getResponse().getContentAsString();

        Long id = objectMapper.readValue(response, Student.class).getId();

        mockMvc.perform(delete("/students/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/students/{id}", id))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST /students should return 400 for invalid input")
    void testCreateStudent_invalidInput() throws Exception {
        Student invalidStudent = new Student("", "invalid-email", -1);
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidStudent)))
                        .andExpect(status().isBadRequest());

    }
}

