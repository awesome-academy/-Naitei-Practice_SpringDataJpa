package com.example.movie.service.impl;

import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.repository.GenreRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    // CREATE
    @Transactional
    @Override
    public Movie createMovie(String title, Year releaseYear, String director, Set<Long> genreIds) {
        Movie movie = new Movie(title, releaseYear, director);

        List<Genre> genres = genreRepository.findAllById(genreIds);
        if (genres.size() != genreIds.size()) {
            throw new RuntimeException("One or more genres not found.");
        }

        for (Genre genre : genres) {
            movie.addGenre(genre);
        }

        return movieRepository.save(movie);
    }

    // READ
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> getMovieByReleaseYear(Year year) {
        return movieRepository.findByReleaseYear(year);
    }

    // UPDATE
    @Transactional
    @Override
    public Movie updateMovieGenres(Long movieId, Set<Long> newGenreIds) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

        Set<Genre> newGenres = new HashSet<>(genreRepository.findAllById(newGenreIds));
        if (newGenres.size() != newGenreIds.size()) {
            throw new RuntimeException("One or more new genres not found.");
        }

        movie.getMovieGenres().removeIf(movieGenre -> !newGenres.contains(movieGenre.getGenre()));

        Set<Genre> currentGenres = movie.getGenres();

        for (Genre genre : newGenres) {
            if (!currentGenres.contains(genre)) {
                movie.addGenre(genre);
            }
        }

        return movieRepository.save(movie);
    }

    // DELETE
    @Transactional
    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }
}