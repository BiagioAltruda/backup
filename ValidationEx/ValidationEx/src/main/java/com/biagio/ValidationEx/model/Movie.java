package com.biagio.ValidationEx.model;

import jakarta.validation.constraints.*;

public class Movie {
	
	@Positive(message="Id cannot be negative")
	private int id;
	@NotBlank(message="Title cannot be blank")
	private String title;
	@NotBlank(message="Director name cannot be blank")
	private String director;
	@NotNull(message="Release year cannot be null")
	@Positive(message="Year has to be positive")
	@Pattern(regexp="^[0-9]{4}$", message = "Year has to be of the form YYYY")
	private Integer releaseYear;
	
	
	
	public Movie() {
	}
	public Movie(@NotBlank(message = "Title cannot be blank") String title,
			@NotBlank(message = "Director name cannot be blank") String director,
			@NotNull(message = "Release year cannot be null") @Positive(message = "Year has to be positive") @Pattern(regexp = "^[0-9]{4}$", message = "Year has to be of the form YYYY") Integer releaseYear) {
		this.title = title;
		this.director = director;
		this.releaseYear = releaseYear;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	
}
