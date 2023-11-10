package com.reto1.myTube.service.song;

import java.util.List;

import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.song.SongFavsViewsRequest;

public interface SongService {

	List<SongDTO> findAll();
	SongDTO findById(int id);
	int create(SongDTO songDto);
	int update(SongDTO songDto);
	int deleteById(int id);
	List<SongDTO> findFavsSongsForUser(int id);
	int updateNumberViews(int idUser, int idSong);
	int insertNumberViews(int idUser, int idSong);
	Integer selectNumberViews(int idUser, int idSong);
	
}
