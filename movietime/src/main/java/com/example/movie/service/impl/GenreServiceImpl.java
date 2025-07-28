package com.example.movie.service.impl;

import com.example.movie.entity.Genre;
import com.example.movie.repository.GenreRepository;
import com.example.movie.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    
    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // CREATE
    @Transactional
    @Override
    public Genre createGenre(String name) {
        if (genreRepository.findByName(name).isPresent()) {
            throw new IllegalStateException("Genre with name '" + name + "' already exists.");
        }
        Genre genre = new Genre(name);
        return genreRepository.save(genre);
    }

    // READ
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    // UPDATE
    @Transactional
    @Override
    public Genre updateGenre(Long id, String newName) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
        genre.setName(newName);
        return genreRepository.save(genre);
    }

    // DELETE
    @Transactional
    @Override
    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not found with id: " + id);
        }
        genreRepository.deleteById(id);
    }
}