package com.reto1.myTube.model.song;

public class SongFavsViewsRequest {
	
	private int id;
	private String title;
	private String author;
	private String url;
	private Integer favorite;
	private Integer views;
	
	public SongFavsViewsRequest() {}
	
	public SongFavsViewsRequest(int id, String title, String author, String url, Integer favorite, Integer views) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.url = url;
		this.favorite = favorite;
		this.views = views;
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
	public Integer getFavorite() {
		return favorite;
	}
	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "SongFavsViewsRequest [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url
				+ ", favorite=" + favorite + ", views=" + views + "]";
	}
	
	

}
