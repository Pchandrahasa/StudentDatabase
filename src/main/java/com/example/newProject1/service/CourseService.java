package com.example.newProject1.service;

import com.example.newProject1.model.Course;
import com.example.newProject1.model.Teacher;
import com.example.newProject1.repository.CourseJpaRepository;
import com.example.newProject1.repository.CourseRepository;
import com.example.newProject1.repository.TeacherJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private TeacherJpaRepository teacherJpaRepository;

    @Override
    public ArrayList<Course> getCourses() {
        List<Course> courseList = courseJpaRepository.findAll();
        return new ArrayList<>(courseList);
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = courseJpaRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return course;
    }

    @Override
    public Course addCourse(Course course) {
        if (course.getTeachers() != null) {
            List<Teacher> updatedTeachers = new ArrayList<>();
            for (Teacher teacher : course.getTeachers()) {
                Teacher presentTeacher = teacherJpaRepository.findById(teacher.getTeacherId()).orElse(null);
                if (presentTeacher != null) {
                    updatedTeachers.add(presentTeacher);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            }
            course.setTeachers(updatedTeachers);
        }
        return courseJpaRepository.save(course);
    }

    @Override
    public Course updateCourse(int courseId, Course course) {
        Course existingCourse = courseJpaRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (course.getCourseName() != null) {
            existingCourse.setCourseName(course.getCourseName());
        }
        if (course.getCredits() > 0) {
            existingCourse.setCredits(course.getCredits());
        }
        if (course.getTeachers() != null) {
            List<Teacher> updatedTeachers = new ArrayList<>();
            for (Teacher teacher : course.getTeachers()) {
                Teacher presentTeacher = teacherJpaRepository.findById(teacher.getTeacherId()).orElse(null);
                if (presentTeacher != null) {
                    updatedTeachers.add(presentTeacher);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            }
            existingCourse.setTeachers(updatedTeachers);
        }

        return courseJpaRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(int courseId) {
        if (!courseJpaRepository.existsById(courseId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        courseJpaRepository.deleteById(courseId);
    }
}
