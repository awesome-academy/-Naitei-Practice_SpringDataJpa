-- =================================================================
--                     SAMPLE DATA FOR MOVIE DATABASE
-- =================================================================
-- Hướng dẫn: Chạy file movie.sql trước, sau đó chạy file này
-- để chèn dữ liệu mẫu vào các bảng.
-- =================================================================
USE movie;

-- ----------------------------
-- Records for table genres
-- ----------------------------
INSERT INTO genres (name) VALUES
('Action'),
('Science Fiction'),
('Drama'),
('Crime'),
('Thriller'),
('Adventure'),
('Animation'),
('Comedy'),
('Fantasy');

-- ----------------------------
-- Records for table movies
-- ----------------------------
INSERT INTO movies (title, release_year, director, created_at, updated_at) VALUES
('The Matrix', 1999, 'Wachowskis', NOW(), NOW()),
('Inception', 2010, 'Christopher Nolan', NOW(), NOW()),
('The Dark Knight', 2008, 'Christopher Nolan', NOW(), NOW()),
('Pulp Fiction', 1994, 'Quentin Tarantino', NOW(), NOW()),
('Forrest Gump', 1994, 'Robert Zemeckis', NOW(), NOW()),
('The Lord of the Rings: The Fellowship of the Ring', 2001, 'Peter Jackson', NOW(), NOW()),
('Spirited Away', 2001, 'Hayao Miyazaki', NOW(), NOW()),
('John Wick', 2014, 'Chad Stahelski', NOW(), NOW()),
('Interstellar', 2014, 'Christopher Nolan', NOW(), NOW());

-- ----------------------------
-- Records for table movie_genres
-- ----------------------------
INSERT INTO movie_genres (movie_id, genre_id) VALUES
-- The Matrix (Action, Science Fiction)
(1, 1),
(1, 2),

-- Inception (Action, Science Fiction, Thriller)
(2, 1),
(2, 2),
(2, 5),

-- The Dark Knight (Action, Crime, Drama)
(3, 1),
(3, 4),
(3, 3),

-- Pulp Fiction (Crime, Drama)
(4, 4),
(4, 3),

-- Forrest Gump (Drama, Comedy)
(5, 3),
(5, 8),

-- The Lord of the Rings (Adventure, Fantasy)
(6, 6),
(6, 9),

-- Spirited Away (Animation, Fantasy)
(7, 7),
(7, 9),

-- John Wick (Action, Thriller)
(8, 1),
(8, 5),

-- Interstellar (Science Fiction, Drama, Adventure)
(9, 2),
(9, 3),
(9, 6);

SELECT 'Sample data inserted successfully!' as status;