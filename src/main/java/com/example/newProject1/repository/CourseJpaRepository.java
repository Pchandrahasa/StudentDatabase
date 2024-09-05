package com.example.newProject1.repository;

import com.example.newProject1.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseJpaRepository extends JpaRepository<Course,Integer> {
}
