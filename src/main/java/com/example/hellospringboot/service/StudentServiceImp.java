package com.example.hellospringboot.service;


import com.example.hellospringboot.model.Student;
import com.example.hellospringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudent() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentByName() {
        return (List<Student>) studentRepository.findStudentByName();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
         studentRepository.save(student) ;
    }

    @Override
    public void remove(Student student) {
            studentRepository.delete(student);
    }
}
