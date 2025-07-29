package org.example.service;

import org.example.models.Post;
import org.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post add(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post update(Long id, Post newPost) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
            post.setStatus(newPost.getStatus());
            post.setCreatedAt(newPost.getCreatedAt());
            post.setUser(newPost.getUser());
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
