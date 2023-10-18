package com.reto1.myTube.model.song;

public class SongDTO {

	private int id;
	private String title;
	private String author;
	private String url;
	
	public SongDTO() {};
	
	public SongDTO(int id, String title, String author, String url) {
		super();
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
		return "SongDTO [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + "]";
	}
	
}
