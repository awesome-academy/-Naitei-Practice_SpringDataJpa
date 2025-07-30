package org.ledang.repository;

import org.ledang.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> getUserByName(String name);
}
