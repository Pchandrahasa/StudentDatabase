package com.example.newProject1.repository;

import com.example.newProject1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepository {

    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    Student addStudent(Student student);

    Student updateStudent(int studentId,Student student);

    void deleteStudent(int studentId);
}
