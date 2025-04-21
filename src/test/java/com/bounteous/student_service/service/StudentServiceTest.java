package com.bounteous.student_service.service;

import com.bounteous.student_service.exception.ResourceNotFoundException;
import com.bounteous.student_service.model.Student;
import com.bounteous.student_service.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImplementation studentServiceImplementation;

    private Student testStudent;

    @BeforeEach
    void setUp(){
        testStudent = new Student("Shaghayegh", "shaghayegh@gmail.com", 21);
    }

    @Test
    void testGetStudentById_found(){
        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        
        Student result = studentServiceImplementation.getStudentById(1L);
        
        assertNotNull(result);
        assertEquals("Shaghayegh", result.getName());
        assertEquals("shaghayegh@gmail.com", result.getEmail());
    }

    @Test
    void testGetStudentById_notFound(){
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentServiceImplementation.getStudentById(2L);
        });
    }

    @Test
    void testCreateStudent_success(){
        Student newStudent = new Student("Alice", "alice@example.com", 19);

        when(studentRepository.save(any(Student.class))).thenReturn(newStudent);

        Student result = studentServiceImplementation.createStudent(newStudent);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());
        assertEquals(19, result.getAge());

        verify(studentRepository, times(1)).save(newStudent);
    }
}
