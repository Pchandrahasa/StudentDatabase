package com.example.newProject1.service;

import com.example.newProject1.model.Course;
import com.example.newProject1.model.Student;
import com.example.newProject1.repository.CourseJpaRepository;
import com.example.newProject1.repository.StudentJpaRepository;
import com.example.newProject1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentRepository {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    public ArrayList<Student> getStudents() {
        List<Student> studentList=studentJpaRepository.findAll();
        ArrayList<Student> students=new ArrayList<>(studentList);
        return students;
    }

    public Student getStudentById(int studentId) {
        Optional<Student> student = studentJpaRepository.findById(studentId);
        return student.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @Override
    public Student addStudent(Student student) {
        Student newStudent=studentJpaRepository.save(student);
        return newStudent;
    }

    public Student updateStudent(int studentId, Student student) {
        Student existingStudent = studentJpaRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }
        if (student.getRollNo() != null) {
            existingStudent.setRollNo(student.getRollNo());
        }
        if (student.getCourse() != null) {
            Course course = courseJpaRepository.findById(student.getCourse().getCourseId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        }

        return studentJpaRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentJpaRepository.deleteById(studentId);

    }
}
