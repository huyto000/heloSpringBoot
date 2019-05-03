package com.example.hellospringboot.repository;

import com.example.hellospringboot.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
    @Query(value="SELECT * FROM student WHERE student.name = 'Alice'",nativeQuery = true)
    public List<Student> findStudentByName();

}
