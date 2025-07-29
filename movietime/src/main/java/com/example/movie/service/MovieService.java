package com.example.movie.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.movie.entity.Movie;

public interface MovieService {
    public Movie createMovie(String title, Year releaseYear, String director, Set<Long> genreIds);

    public List<Movie> getAllMovies();

    public Optional<Movie> getMovieById(Long id);

    public List<Movie> getMovieByTitle(String title);    

    public List<Movie> getMovieByReleaseYear(Year year);

    public Movie updateMovieGenres(Long movieId, Set<Long> newGenreIds);

    public void deleteMovie(Long id);
}
