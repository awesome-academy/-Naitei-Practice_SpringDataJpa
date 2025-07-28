package com.example.movie.service;

import java.util.List;
import java.util.Optional;

import com.example.movie.entity.Genre;

public interface GenreService {
    public Genre createGenre(String name);

    public List<Genre> getAllGenres();

    public Optional<Genre> getGenreById(Long id);

    public Genre updateGenre(Long id, String newName);

    public void deleteGenre(Long id);
}
