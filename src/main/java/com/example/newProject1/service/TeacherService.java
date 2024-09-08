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
        Teacher teacher = teacherJpaRepository.findById(teacherId).orElse(null);
        if (teacher != null) {
            return teacher;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        if (teacher.getCourses() != null) {
            ArrayList<Course> updatedCourse = new ArrayList<>();
            for (Course course : teacher.getCourses()) {
                Course presentCourse = courseJpaRepository.findById(course.getCourseId()).orElse(null);
                if (presentCourse != null) {
                    updatedCourse.add(presentCourse);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            }
            teacher.setCourses(updatedCourse);
        }
        return teacherJpaRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(int teacherId, Teacher teacher) {
        Teacher existingTeacher = teacherJpaRepository.findById(teacherId).orElse(null);
        if (existingTeacher != null) {
            if (teacher.getTeacherName() != null) {
                existingTeacher.setTeacherName(teacher.getTeacherName());
            }

            if (teacher.getDepartment() != null) {
                existingTeacher.setDepartment(teacher.getDepartment());
            }

            if (teacher.getCourses() != null) {
                ArrayList<Course> updatedCourse = new ArrayList<>();
                for (Course course : teacher.getCourses()) {
                    Course presentCourse = courseJpaRepository.findById(course.getCourseId()).orElse(null);
                    if (presentCourse != null) {
                        updatedCourse.add(presentCourse);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                    }
                }
                existingTeacher.setCourses(updatedCourse);
            }
            return teacherJpaRepository.save(existingTeacher);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTeacher(int teacherId) {
        if (teacherJpaRepository.existsById(teacherId)) {
            teacherJpaRepository.deleteById(teacherId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND );
        }
    }
}
