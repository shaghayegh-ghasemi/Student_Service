package com.bounteous.student_service.service;

import com.bounteous.student_service.exception.ResourceNotFoundException;
import com.bounteous.student_service.model.Student;
import com.bounteous.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
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

    @Bean
    CommandLineRunner runner(StudentRepository repository){
        return args -> {
            repository.save(new Student("Shaghayegh", "shaghayegh.ghasemi@bounteous.com", 26));
            repository.save(new Student("Milad", "milad@gmail.com", 19));
            repository.save(new Student("Sarah", "sarah@yahoo.com", 22));
            repository.save(new Student("Alice", "alice@yahoo.com", 28));
            repository.save(new Student("Mark", "mark@concordi.ca", 24));
            repository.save(new Student("Javad", "javad@example.com", 21));
        };
    }
}
