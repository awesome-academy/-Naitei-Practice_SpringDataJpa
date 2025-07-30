package com.example.library.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;

    public Author(){}

    public Author(String name, LocalDate birthday){
        this.name = name;
        this.birthday = birthday; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
