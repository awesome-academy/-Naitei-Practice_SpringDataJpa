DROP DATABASE IF EXISTS movie;

CREATE DATABASE movie
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE movie;

-- Bảng thể loại (genres)
CREATE TABLE genres (
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Bảng phim (movies)
CREATE TABLE movies (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    release_year YEAR,
    director     VARCHAR(255),
    created_at   TIMESTAMP(6) NOT NULL,
    updated_at   TIMESTAMP(6) NOT NULL
);

-- Bảng trung gian
CREATE TABLE movie_genres (
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    movie_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT fk_movie
        FOREIGN KEY (movie_id)
        REFERENCES movies (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_genre
        FOREIGN KEY (genre_id)
        REFERENCES genres (id)
        ON DELETE CASCADE,
    CONSTRAINT uk_movie_genre UNIQUE (movie_id, genre_id)
);