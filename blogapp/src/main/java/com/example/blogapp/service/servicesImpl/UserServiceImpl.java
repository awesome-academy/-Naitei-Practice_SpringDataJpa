package com.example.blogapp.service.servicesImpl;

import com.example.blogapp.model.User;
import com.example.blogapp.repository.repositories.UserRepository;
import com.example.blogapp.service.services.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
}
