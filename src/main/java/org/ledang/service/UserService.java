package org.ledang.service;

import org.ledang.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(User user, int userId);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
    void deleteUserById(int id);
}
