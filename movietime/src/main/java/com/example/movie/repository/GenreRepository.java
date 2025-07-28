package com.example.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Optional<Genre> findByName(String name);
}
