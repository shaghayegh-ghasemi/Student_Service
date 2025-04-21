package com.bounteous.student_service.service;

import com.bounteous.student_service.exception.ResourceNotFoundException;
import com.bounteous.student_service.model.Student;
import com.bounteous.student_service.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student currInfo = getStudentById(id);
        currInfo.setName(student.getName());
        currInfo.setEmail(student.getEmail());
        currInfo.setAge(student.getAge());
        return studentRepository.save(currInfo);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
