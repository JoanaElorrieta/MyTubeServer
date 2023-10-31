package com.reto1.myTube.model.user;

import java.util.List;

import com.reto1.myTube.model.song.SongDTO;

public class UserFavsSongGetRequest {

	private int id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String accessToken;
	private List<SongDTO> listSongFavs;
	private List<Integer> views;
	
	public UserFavsSongGetRequest() {}


	public UserFavsSongGetRequest(int id, String name, String lastName, String email, String password, String accessToken,
			List<SongDTO> listSongFavs, List<Integer> views) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.accessToken = accessToken;
		this.listSongFavs = listSongFavs;
		this.views = views;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public List<SongDTO> getListSongFavs() {
		return listSongFavs;
	}

	public void setListSongFavs(List<SongDTO> listSongFavs) {
		this.listSongFavs = listSongFavs;
	}

	public List<Integer> getViews() {
		return views;
	}

	public void setViews(List<Integer> views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "UserFavsSongGetRequest [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", listSongFavs=" + listSongFavs + ", views=" + views + "]";
	}

	
}
