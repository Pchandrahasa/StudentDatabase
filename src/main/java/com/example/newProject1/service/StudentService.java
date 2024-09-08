package com.example.newProject1.service;

import com.example.newProject1.model.Course;
import com.example.newProject1.model.Student;
import com.example.newProject1.model.Teacher;
import com.example.newProject1.repository.CourseJpaRepository;
import com.example.newProject1.repository.StudentJpaRepository;
import com.example.newProject1.repository.StudentRepository;
import com.example.newProject1.repository.TeacherJpaRepository;
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

    @Autowired
    private TeacherJpaRepository teacherJpaRepository;

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
        if (student.getCourse() != null) {
            Course course = courseJpaRepository.findById(student.getCourse().getCourseId()).orElse(null);
            if (course == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
            }
            if(course.getTeachers()!=null){
                ArrayList<Teacher> updateTeacher=new ArrayList<>();
                for(Teacher teacher: course.getTeachers()){
                    int teacherId=teacher.getTeacherId();
                    Teacher presentTeacher=teacherJpaRepository.findById(teacherId).get();
                    if(presentTeacher!=null){
                        updateTeacher.add(presentTeacher);
                    }
                    else{
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                    }
                }
                course.setTeachers(updateTeacher);
            }
            student.setCourse(course);
        }

        Student newStudent=studentJpaRepository.save(student);
        return newStudent;
    }

    public Student updateStudent(int studentId, Student student) {
        Student existingStudent = studentJpaRepository.findById(studentId).get();

        if (existingStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }

        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }
        if (student.getRollNo() != null) {
            existingStudent.setRollNo(student.getRollNo());
        }

        if (student.getCourse() != null) {
            Course course = courseJpaRepository.findById(student.getCourse().getCourseId()).orElse(null);
            if (course == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
            }
            if(course.getTeachers()!=null){
                ArrayList<Teacher> updateTeacher=new ArrayList<>();
                for(Teacher teacher: course.getTeachers()){
                    int teacherId=teacher.getTeacherId();
                    Teacher presentTeacher=teacherJpaRepository.findById(teacherId).get();
                    if(presentTeacher!=null){
                        updateTeacher.add(presentTeacher);
                    }
                    else{
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                    }
                }
                course.setTeachers(updateTeacher);
            }
            existingStudent.setCourse(course);
        }

        return studentJpaRepository.save(existingStudent);
    }

        @Override
    public void deleteStudent(int studentId) {
        studentJpaRepository.deleteById(studentId);

    }
}
