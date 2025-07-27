package com.example.blogapp.service;

import com.example.blogapp.model.Post;
import com.example.blogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post, Long> implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    protected JpaRepository<Post, Long> getRepository() {
        return postRepository;
    }
}
