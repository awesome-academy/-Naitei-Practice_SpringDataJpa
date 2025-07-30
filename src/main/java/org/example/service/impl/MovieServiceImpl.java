package org.example.service.impl;

import org.example.entity.Movie;
import org.example.repository.MovieRepository;
import org.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        if (movie.getId() != null && movieRepository.existsById(movie.getId())) {
            return movieRepository.save(movie);
        } else {
            throw new IllegalArgumentException("Movie with ID " + movie.getId() + " does not exist for update.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMoviesByTitleContaining(String title) {

        return movieRepository.findAll().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMoviesByReleaseYear(int year) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getReleaseYear() == year)
                .collect(java.util.stream.Collectors.toList());
    }
}