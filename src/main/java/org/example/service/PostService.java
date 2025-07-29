package org.example.service;

import org.example.models.Post;

import java.util.List;

public interface PostService {
    Post add(Post post);
    List<Post> getAll();
    Post update(Long id, Post newPost);
    void delete(Long id);
}
