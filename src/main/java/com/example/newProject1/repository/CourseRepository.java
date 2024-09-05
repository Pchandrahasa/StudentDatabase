package com.example.newProject1.repository;

import com.example.newProject1.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseRepository {
    ArrayList<Course> getCourses();

    Course getCourseById(int courseId);

    Course addCourse(Course course);

    Course updateCourse(int courseId,Course course);

    void deleteCourse(int courseId);
}
