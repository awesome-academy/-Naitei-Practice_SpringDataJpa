package com.example.practice_spring_data_jpa.user.mapper;

import com.example.practice_spring_data_jpa.user.dto.request.CreateUserRequest;
import com.example.practice_spring_data_jpa.user.dto.request.LoginRequest;
import com.example.practice_spring_data_jpa.user.dto.response.UserResponse;
import com.example.practice_spring_data_jpa.user.entity.User;

public class UserMapper {
    public static User toEntity(LoginRequest dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponse toDto(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(CreateUserRequest dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return user;
    }
}
