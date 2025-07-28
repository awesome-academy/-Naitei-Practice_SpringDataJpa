package org.example.controller;

import org.example.entity.Author;
import org.example.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> authors()
    {
        return authorService.getAllAuthor();
    }
}
