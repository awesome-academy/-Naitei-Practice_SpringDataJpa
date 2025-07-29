package com.example.practice_spring_data_jpa.user.service;

import com.example.practice_spring_data_jpa.user.dto.request.ChangePasswordRequest;
import com.example.practice_spring_data_jpa.user.dto.request.CreateUserRequest;
import com.example.practice_spring_data_jpa.user.dto.request.LoginRequest;
import com.example.practice_spring_data_jpa.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    /**
     * create user
     *
     * @param createUserRequest đây là thông tin để tạo tài khoản
     * @return trả về thông tin người dùng
     */
    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse login(LoginRequest loginRequest);

    void deleteUser(String username);

    /**
     * change user password
     *
     * @param changePasswordRequest
     */
    void changePassword(ChangePasswordRequest changePasswordRequest);

    List<UserResponse> findAllUsers();
}
