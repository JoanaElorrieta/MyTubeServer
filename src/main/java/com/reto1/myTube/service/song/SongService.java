package com.reto1.myTube.service.song;

import java.util.List;

import com.reto1.myTube.model.song.SongDTO;

public interface SongService {

	List<SongDTO> findAll();
	SongDTO findById(int id);
	int create(SongDTO songDto);
	int update(SongDTO songDto);
	int deleteById(int id);
	
}
