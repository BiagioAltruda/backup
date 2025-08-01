package com.biagio.CRUDex.model;

import jakarta.validation.constraints.*;

public class Libro {
	@NotNull(message="id can't be null")
	@Positive(message="id can't be negative")
	private int id;
	@NotBlank(message="author name cannot be blank")
	private String author;
	@NotBlank(message="title cannot be blank")
	private String title;
	@NotBlank(message="release date cannot be blank")
	private String releaseDate;

	public Libro() {
		
	}
	
	public Libro(String autore) {
		super();
		this.author = autore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String autore) {
		this.author = autore;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
