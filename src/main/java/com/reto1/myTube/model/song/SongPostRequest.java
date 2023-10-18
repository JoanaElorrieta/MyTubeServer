package com.reto1.myTube.model.song;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SongPostRequest {

	@NotNull(message = "El id no puede ser Nulo")
	@NotBlank(message = "El id no puede ser Blanco")
	@NotEmpty(message = "El id no puede ser Vacio")
	private int id;
	
	@NotNull(message = "El titulo no puede ser Nulo")
	@NotBlank(message = "El titulo no puede ser Blanco")
	@NotEmpty(message = "El titulo no puede ser Vacio")
	private String title;
	
	@NotNull(message = "El author no puede ser Nulo")
	@NotBlank(message = "El author no puede ser Blanco")
	@NotEmpty(message = "El author no puede ser Vacio")
	private String author;
	
	@NotNull(message = "El url no puede ser Nulo")
	@NotBlank(message = "El url no puede ser Blanco")
	@NotEmpty(message = "El url no puede ser Vacio")
	private String url;
	
	public SongPostRequest() {};
	
	public SongPostRequest(	
	@NotNull(message = "El id no puede ser Nulo")@NotBlank(message = "El id no puede ser Blanco")@NotEmpty(message = "El id no puede ser Vacio")int id,
	@NotNull(message = "El titulo no puede ser Nulo")@NotBlank(message = "El titulo no puede ser Blanco")@NotEmpty(message = "El titulo no puede ser Vacio")String title,
	@NotNull(message = "El author no puede ser Nulo")@NotBlank(message = "El author no puede ser Blanco")@NotEmpty(message = "El author no puede ser Vacio")String author,
	@NotNull(message = "El url no puede ser Nulo")@NotBlank(message = "El url no puede ser Blanco")@NotEmpty(message = "El url no puede ser Vacio")String url
	) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SongPostRequest [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + "]";
	}
	
}
