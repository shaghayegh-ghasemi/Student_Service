package com.bounteous.student_service.repository;

import com.bounteous.student_service.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("save, retrieve a student by id")
    void testSaveAndFindById() {

        Student student = new Student("Reza", "reza@example.com", 30);
        student = studentRepository.save(student); // insert into DB


        Optional<Student> result = studentRepository.findById(student.getId());

        assertTrue(result.isPresent());
        assertEquals("Reza", result.get().getName());
        assertEquals("reza@example.com", result.get().getEmail());
    }

    @Test
    @DisplayName("It should return empty if student not found")
    void testFindById_notFound() {
        Optional<Student> result = studentRepository.findById(999L);
        assertTrue(result.isEmpty());
    }
}
