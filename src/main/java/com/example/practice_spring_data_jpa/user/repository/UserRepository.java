package com.example.practice_spring_data_jpa.user.repository;

import com.example.practice_spring_data_jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();
}
