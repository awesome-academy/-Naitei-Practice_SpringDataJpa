package com.example.practice_spring_data_jpa.user.service.impl;

import com.example.practice_spring_data_jpa.user.dto.request.ChangePasswordRequest;
import com.example.practice_spring_data_jpa.user.dto.request.CreateUserRequest;
import com.example.practice_spring_data_jpa.user.dto.request.LoginRequest;
import com.example.practice_spring_data_jpa.user.dto.response.UserResponse;
import com.example.practice_spring_data_jpa.user.entity.User;
import com.example.practice_spring_data_jpa.user.mapper.UserMapper;
import com.example.practice_spring_data_jpa.user.repository.UserRepository;
import com.example.practice_spring_data_jpa.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            throw new RuntimeException("User not found");
        }
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return UserMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest createUserRequest) {

        try {
            if (userRepository.existsByUsername(createUserRequest.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            User user = UserMapper.toEntity(createUserRequest);
            User savedUser = userRepository.save(user);
            return UserMapper.toDto(savedUser);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        try {
            if (!userRepository.existsByUsername(username)) {
                throw new RuntimeException("Username not found");
            }
            User user = userRepository.findByUsername(username);
            userRepository.deleteById(user.getId());
            if (userRepository.existsByUsername(username)) {
                throw new RuntimeException("User still exists");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        try {
            if (!userRepository.existsByUsername(changePasswordRequest.getUsername())) {
                throw new RuntimeException("Username not found");
            }
            User user = userRepository.findByUsername(changePasswordRequest.getUsername());
            if (!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
                throw new RuntimeException("Old password not match");
            }
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
