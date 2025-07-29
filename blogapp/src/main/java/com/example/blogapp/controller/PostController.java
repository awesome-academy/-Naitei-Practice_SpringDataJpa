package com.example.blogapp.controller;

import com.example.blogapp.model.Post;
import com.example.blogapp.model.User;
import com.example.blogapp.service.services.PostService;
import com.example.blogapp.service.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*") // Cho phép gọi từ Postman, trình duyệt khác domain
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // GET all posts
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    // GET post by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new post
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        if (post.getUser() == null || post.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }

        Optional<User> userOptional = userService.findById(post.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }

        post.setUser(userOptional.get());
        return ResponseEntity.ok(postService.save(post));
    }

    // PUT update post
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return postService.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(updatedPost.getTitle());
                    existingPost.setContent(updatedPost.getContent());

                    if (updatedPost.getUser() != null && updatedPost.getUser().getId() != null) {
                        userService.findById(updatedPost.getUser().getId())
                                .ifPresent(existingPost::setUser);
                    }

                    return ResponseEntity.ok(postService.save(existingPost));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
