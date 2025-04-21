package com.bounteous.student_service.controller;



import com.bounteous.student_service.exception.ResourceNotFoundException;
import com.bounteous.student_service.model.Student;
import com.bounteous.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentViewController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/ui/students")
    public String showStudentList(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // corresponds to students.html
    }

    @GetMapping("/ui/student-form")
    public String showStudentForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            try {
                Student student = studentService.getStudentById(id);
                model.addAttribute("student", student);
            } catch (ResourceNotFoundException ex) {
                model.addAttribute("error", "No student found with ID " + id);
            }
        }
        return "student-form";
    }

    @PostMapping("/ui/add-form")
    public String addStudent(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam int age,
                             Model model) {
        try {
            Student newStudent = new Student(name, email, age);
            Student created = studentService.createStudent(newStudent);
            model.addAttribute("createdStudent", created);
        } catch (Exception ex) {
            model.addAttribute("error", "Failed to add student.");
        }
        return "add-form";
    }

    @GetMapping("/ui/add-form")
    public String showAddForm() {
        return "add-form";
    }

    @PostMapping("/ui/update-form")
    public String updateStudent(@RequestParam Long id,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) Integer age,
                                Model model) {
        try {
            Student existing = studentService.getStudentById(id);

            // Only update non-empty fields
            if (name != null && !name.trim().isEmpty()) {
                existing.setName(name);
            }
            if (email != null && !email.trim().isEmpty()) {
                existing.setEmail(email);
            }
            if (age != null && age > 0) {
                existing.setAge(age);
            }

            Student updatedStudent = studentService.updateStudent(id, existing);
            model.addAttribute("updatedStudent", updatedStudent);

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", "Student not found with ID: " + id);
        }

        return "update-form";
    }


    @GetMapping("/ui/update-form")
    public String showUpdateForm() {
        return "update-form";
    }

    @PostMapping("/ui/delete-form")
    public String deleteStudent(@RequestParam Long id, Model model) {
        try {
            Student student = studentService.getStudentById(id); // fetch before delete
            String name = student.getName();
            studentService.deleteStudent(id);
            model.addAttribute("deletedStudentName", name);
        } catch (Exception e) {
            model.addAttribute("error", "Could not delete student with ID: " + id);
        }
        return "delete-form";
    }

    @GetMapping("/ui/delete-form")
    public String showDeleteForm() {
        return "delete-form";
    }
}