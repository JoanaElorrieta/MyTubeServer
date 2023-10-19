package com.reto1.myTube.repository.song;

import java.util.List;

import com.reto1.myTube.model.song.SongDAO;

public interface SongRepository {

	List<SongDAO> findAll();
	SongDAO findById(int id);
	int create(SongDAO songDao);
	int update(SongDAO songDao);
	int deleteById(int id);
	List<SongDAO> getFavsSongsForCertainUser(int id);
	
}
