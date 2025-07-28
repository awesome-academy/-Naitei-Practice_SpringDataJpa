package com.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // hoặc AUTO

    private Long id;
    private String title;
    private String code;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Constructors
    public Course() {}
    public Course(Long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}
