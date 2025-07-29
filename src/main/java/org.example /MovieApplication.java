package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class MovieApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MovieApplication.class, args);
        MovieService movieService = context.getBean(MovieService.class);

        Movie m = new Movie();
        m.setTitle("MyMovie");
        m.setGenre("Genre");
        m.setDurationMinutes(148);

        movieService.save(m);
        movieService.getAll().forEach(movie -> System.out.println(movie.getTitle()));
    }
}

