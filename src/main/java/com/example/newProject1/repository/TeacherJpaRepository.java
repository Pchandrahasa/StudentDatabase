package com.example.newProject1.repository;


import com.example.newProject1.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherJpaRepository extends JpaRepository<Teacher,Integer> {
}
