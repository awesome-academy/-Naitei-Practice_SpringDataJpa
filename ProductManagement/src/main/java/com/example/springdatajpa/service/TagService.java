package com.example.springdatajpa.service;

import com.example.springdatajpa.model.Tag;
import java.util.List;

public interface TagService {
    Tag createTag(String name);
    List<Tag> getAllTags();
    void deleteTag(Long id);
}