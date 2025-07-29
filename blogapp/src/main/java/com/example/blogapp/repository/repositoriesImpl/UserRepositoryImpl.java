package com.example.blogapp.repository.repositoriesImpl;

import com.example.blogapp.model.User;
import com.example.blogapp.repository.repositories.UserRepository;
import com.example.blogapp.repository.repositoriesImpl.BaseRepositoryImpl;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public UserRepositoryImpl() {
        super(User.class);
    }

}
