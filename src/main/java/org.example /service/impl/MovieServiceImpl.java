package org.example.service.impl;

import org.example.entity.Movie;
import org.example.repository.MovieRepository;
import org.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(updatedMovie.getTitle());
                    movie.setGenre(updatedMovie.getGenre());
                    movie.setDuration(updatedMovie.getDuration());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    updatedMovie.setId(id);
                    return movieRepository.save(updatedMovie);
                });
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
