package com.example.springdatajpa.service.impl;

import com.example.springdatajpa.model.Tag;
import com.example.springdatajpa.repository.TagRepository;
import com.example.springdatajpa.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag createTag(String name) {
        if (tagRepository.findByName(name).isPresent()) {
            throw new IllegalStateException("Tag with name '" + name + "' already exists.");
        }
        Tag newTag = new Tag();
        newTag.setName(name);
        return tagRepository.save(newTag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new IllegalStateException("Tag with id '" + id + "' does not exist.");
        }
        tagRepository.deleteById(id);
    }
}