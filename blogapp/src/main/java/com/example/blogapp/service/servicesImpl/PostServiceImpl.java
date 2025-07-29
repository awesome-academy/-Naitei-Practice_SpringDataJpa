package com.example.blogapp.service.servicesImpl;

import com.example.blogapp.model.Post;
import com.example.blogapp.repository.repositories.PostRepository;
import com.example.blogapp.service.services.PostService;

public class PostServiceImpl extends BaseServiceImpl<Post, Long> implements PostService {

    public PostServiceImpl(PostRepository postRepository) {
        super(postRepository);
    }
}
