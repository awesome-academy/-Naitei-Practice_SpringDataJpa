package org.example.service;

import org.example.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Optional<Movie> getMovieById(Long id);
    List<Movie> getAllMovies();
    void deleteMovieById(Long id);
    Movie updateMovie(Movie movie);
    List<Movie> findMoviesByTitleContaining(String title);
    List<Movie> findMoviesByReleaseYear(int year);
}