package org.example.models;

//import jakarta.persistence.*;

import javax.persistence.*;
import java.util.List;


@Entity //JPA Entity Mapping
@Table(name = "students") // ánh xạ với bảng "student"
public class Student {
    @Id //khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng ID
    private Long id;

    private String name;

    private String email;

    private int age;

    @ManyToOne // nhiều sinh viên thuộc 1 lớp học
    @JoinColumn(name = "school_id")  // khóa ngoại trỏ đến bảng class
    private School school;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentSubject> studentSubjects;

//    @ManyToMany
//    @JoinTable(
//            name = "student_subject",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id")
//    )
//    private List<Subject> subjects;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classRoom=" + (school != null ? school.getName() : "null") +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

//docs validator: https://docs.jboss.org/hibernate/validator/9.0/reference/en-US/html_single/#validator-gettingstarted
