package com.bounteous.student_service.service;

import com.bounteous.student_service.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student createStudent(Student student); // create
    Student getStudentById(Long id); // read
    Student updateStudent(Long id, Student student); // update
    void deleteStudent(Long id); // delete
}
