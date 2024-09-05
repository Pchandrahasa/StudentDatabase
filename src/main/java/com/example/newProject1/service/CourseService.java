package com.example.newProject1.service;

import com.example.newProject1.model.Course;
import com.example.newProject1.repository.CourseJpaRepository;
import com.example.newProject1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public ArrayList<Course> getCourses() {
        List<Course> courseList = courseJpaRepository.findAll();
        return new ArrayList<>(courseList);
    }

    @Override
    public Course getCourseById(int courseId) {
        Optional<Course> courseOptional = courseJpaRepository.findById(courseId);
        return courseOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    @Override
    public Course addCourse(Course course) {
         courseJpaRepository.save(course);
         return course;
    }

    @Override
    public Course updateCourse(int courseId, Course course) {
        Course exCourse = courseJpaRepository.findById(courseId).get();
        if (exCourse!=null) {
            Course existingCourse = exCourse;
            if (course.getCourseName() != null) {
                existingCourse.setCourseName(course.getCourseName());
            }
            if (course.getCredits() != 0) {
                existingCourse.setCredits(course.getCredits());
            }
            return courseJpaRepository.save(existingCourse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        if (courseJpaRepository.existsById(courseId)) {
            courseJpaRepository.deleteById(courseId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
    }
}
