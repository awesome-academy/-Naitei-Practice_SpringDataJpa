package com.example.movie.entity;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "movies")
public class Movie extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(name = "release_year")
	private Year releaseYear;

	private String director;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<MovieGenre> movieGenres = new HashSet<>();

	public Movie() {
	}

	public Movie(String title) {
		this.title = title;
	}

	public Movie(String title, Year releaseYear, String director) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.director = director;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Year getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Year releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Set<MovieGenre> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(Set<MovieGenre> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public void addGenre(Genre genre) {
		MovieGenre movieGenre = new MovieGenre(this, genre);
		this.movieGenres.add(movieGenre);
	}

	public Set<Genre> getGenres() {
		return this.movieGenres.stream()
				.map(MovieGenre::getGenre)
				.collect(Collectors.toSet());
	}
}