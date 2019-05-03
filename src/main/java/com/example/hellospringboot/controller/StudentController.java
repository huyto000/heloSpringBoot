package com.example.hellospringboot.controller;

import com.example.hellospringboot.model.Student;
import com.example.hellospringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @RequestMapping(value ="/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents()
    {
        List<Student> students = studentService.findAllStudent();
        if(students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<>(students, HttpStatus.OK);
    }
    @RequestMapping(value ="/students/search", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getSearchStudents()
    {
        List<Student> students = studentService.findStudentByName();
        if(students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(value="/students/{id}",method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
       Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(student.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(
            @PathVariable("id") Integer id,
            @RequestBody Student student) {
        Optional<Student> currentStudent = studentService.findById(id);

        if (!currentStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentStudent.get().setName(student.getName());
        currentStudent.get().setEmail(student.getEmail());
        currentStudent.get().setCourse(student.getCourse());

        studentService.save(currentStudent.get());
        return new ResponseEntity<>(currentStudent.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/students", method= RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        studentService.save(student);
        System.out.println("Sending");
        return new ResponseEntity<>(student, HttpStatus.CREATED);
}
    @RequestMapping(value = "/students/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Student> deletestudent(
            @PathVariable("id") Integer id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.remove(student.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
