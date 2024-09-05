package com.example.newProject1.repository;

import com.example.newProject1.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TeacherRepository {
    ArrayList<Teacher> getTeacher();

    Teacher addTeacher(Teacher teacher);

    Teacher updateTeacher(int teacherId, Teacher teacher);

    Teacher getTeacherById(int teacherId);

    void deleteTeacher(int teacherId);
}
