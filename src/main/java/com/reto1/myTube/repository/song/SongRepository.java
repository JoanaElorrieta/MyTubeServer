package com.reto1.myTube.repository.song;

import java.util.List;

import com.reto1.myTube.model.song.SongDAO;

public interface SongRepository {

	List<SongDAO> findAll();
	SongDAO findById(int id);
	int create(SongDAO songDao);
	int update(SongDAO songDao);
	int deleteById(int id);
	Integer getFavsSongsForCertainUser(int id, int idSong);
	int updateNumberViews(int idUser, int idSong);
	int insertNumberViews(int idUser, int idSong);
	Integer selectNumberViews(int idUser, int idSong);
	
}
