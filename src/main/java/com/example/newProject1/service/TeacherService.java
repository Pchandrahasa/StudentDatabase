package com.example.newProject1.service;

import com.example.newProject1.model.Course;
import com.example.newProject1.model.Teacher;
import com.example.newProject1.repository.CourseJpaRepository;
import com.example.newProject1.repository.TeacherJpaRepository;
import com.example.newProject1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements TeacherRepository {

    @Autowired
    private TeacherJpaRepository teacherJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public ArrayList<Teacher> getTeacher() {
        List<Teacher> teacherList = teacherJpaRepository.findAll();
        return new ArrayList<>(teacherList);
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        Optional<Teacher> teacherOptional = teacherJpaRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
    }

    @Override
    public void deleteTeacher(int teacherId) {
        if (teacherJpaRepository.existsById(teacherId)) {
            teacherJpaRepository.deleteById(teacherId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherJpaRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(int teacherId, Teacher teacher) {
        Optional<Teacher> existingTeacherOptional = teacherJpaRepository.findById(teacherId);
        if (existingTeacherOptional.isPresent()) {
            Teacher existingTeacher = existingTeacherOptional.get();
            // Update fields
            if (teacher.getTeacherName() != null) {
                existingTeacher.setTeacherName(teacher.getTeacherName());
            }
            if (teacher.getDepartment() != null) {
                existingTeacher.setDepartment(teacher.getDepartment());
            }
            if (teacher.getCourses()!=null){
               ArrayList<Course> updatedCourse=new ArrayList<>();
               for(Course course:teacher.getCourses()){
                  int courseId=course.getCourseId();
                  Course PresentCourse=courseJpaRepository.findById(courseId).get();
                  if(PresentCourse!=null) {
                      updatedCourse.add(PresentCourse);
                  }else{
                      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                  }
               }
               existingTeacher.setCourses(updatedCourse);
            }

            return teacherJpaRepository.save(existingTeacher);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
    }
}
