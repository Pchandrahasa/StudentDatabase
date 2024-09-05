package com.example.newProject1.controller;


import com.example.newProject1.model.Teacher;
import com.example.newProject1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public ArrayList<Teacher> getTeachers(){
        return teacherService.getTeacher();
    }

    @GetMapping("/teachers/{teacherId}")
    public  Teacher getTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping("/teachers")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    @PutMapping("/teachers/{teacherId}")
    public Teacher updateTeacher(@PathVariable("teacherId") int teacherId,@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacherId,teacher);
    }

    @DeleteMapping("/teachers/{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") int teacherId){
         teacherService.deleteTeacher(teacherId);
    }

}
