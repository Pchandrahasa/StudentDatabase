package com.example.newProject1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherid")
    private int teacherId;

    @Column(name = "teachername")
    private String teacherName;

    @Column(name = "department")
    private String department;

    @ManyToMany
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "teacherid"),
            inverseJoinColumns = @JoinColumn(name = "courseid")
    )
    @JsonIgnoreProperties("teachers")
    private List<Course> courses = new ArrayList<>();
}
