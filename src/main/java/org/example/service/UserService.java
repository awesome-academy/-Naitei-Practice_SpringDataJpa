package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void deleteUser(User user);

    public List<User> findAll();

    public User findById(int id);



}
