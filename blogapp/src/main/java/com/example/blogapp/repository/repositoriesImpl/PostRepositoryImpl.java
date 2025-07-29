package com.example.blogapp.repository.repositoriesImpl;

import com.example.blogapp.model.Post;
import com.example.blogapp.repository.repositories.PostRepository;
import com.example.blogapp.repository.repositoriesImpl.BaseRepositoryImpl;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class PostRepositoryImpl extends BaseRepositoryImpl<Post, Long> implements PostRepository {

    @PersistenceContext
    private EntityManager em;

    public PostRepositoryImpl() {
        super(Post.class);
    }

    // custom method nếu có
}
