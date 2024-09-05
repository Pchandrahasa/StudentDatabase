package com.example.newProject1.controller;

import com.example.newProject1.model.Student;
import com.example.newProject1.repository.CourseRepository;
import com.example.newProject1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/students")
    public ArrayList<Student> getStudents(){
        return service.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return service.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student){
        return service.updateStudent(studentId,student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        service.deleteStudent(studentId);
    }

}
