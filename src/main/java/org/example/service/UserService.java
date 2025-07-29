package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {

    User add(User user);

    List<User> getAllUser();

    User update(Long id, User newUser);

    void delete(Long id);


}
