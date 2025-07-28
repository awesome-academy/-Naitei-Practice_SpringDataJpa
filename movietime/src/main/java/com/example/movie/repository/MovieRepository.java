package com.example.movie.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findByTitle(String title);

    public List<Movie> findByReleaseYear(Year year);
}
