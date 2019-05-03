package com.example.hellospringboot.service;

import com.example.hellospringboot.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudent();
    List<Student> findStudentByName();
    Optional<Student> findById(Integer id);
    void save(Student student);
    void remove(Student student);
}
