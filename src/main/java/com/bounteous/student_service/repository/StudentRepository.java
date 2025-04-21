package com.bounteous.student_service.repository;

import com.bounteous.student_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JPA provides CRUD ops, no need to implement it
}
