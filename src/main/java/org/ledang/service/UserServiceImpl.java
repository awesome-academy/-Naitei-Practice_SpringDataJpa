package org.ledang.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ledang.entity.User;
import org.ledang.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Transactional
    public User addUser(User user) {
        User userAdd = new User();
        userAdd.setName(user.getName());
        userAdd.setAge(user.getAge());
        return userRepository.save(userAdd);
    }

    @Transactional
    public User updateUser(User user, int userId) {
        try {
            User userUpdate = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            userUpdate.setName(user.getName());
            userUpdate.setAge(user.getAge());
            return userRepository.save(userUpdate);
        } catch (RuntimeException e) {
            System.out.println("Error in updateUser: " + e.getMessage());
            return  null;
        }
    }

    @Transactional
    public User getUserById(int id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (RuntimeException e) {
            System.out.println("Error in getUserById: " + e.getMessage());
            return  null;
        }
    }

    @Transactional
    public User getUserByName(String name) {
        try {
            return userRepository.getUserByName(name)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (RuntimeException e) {
            System.out.println("Error in getUserByName: " + e.getMessage());
            return  null;
        }
    }

    @Transactional
    public List<User> getAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(int id) {
        try {
            User userDelete = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            userRepository.delete(userDelete);
        }  catch (RuntimeException e) {
            System.out.println("Error in deleteUserById: " + e.getMessage());
        }
    }
}
