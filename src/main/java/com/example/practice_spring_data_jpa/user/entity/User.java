package com.example.practice_spring_data_jpa.user.entity;

import com.example.practice_spring_data_jpa.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="full_name")
    private String fullName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="avatar")
    private String avatar;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.user;

    @Column(name="activation_date")
    private Date activationDate;

    @Column(name="created_at")
    private OffsetDateTime createdAt =  OffsetDateTime.now();

}
