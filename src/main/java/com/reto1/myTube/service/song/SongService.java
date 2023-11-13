package com.reto1.myTube.service.song;

import java.util.List;

import com.reto1.myTube.exception.song.SongConstraintException;
import com.reto1.myTube.exception.song.SongNotFoundException;
import com.reto1.myTube.exception.user.UserNumberViewsConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.song.SongDTO;

public interface SongService {

	List<SongDTO> findAll() throws SongNotFoundException;
	SongDTO findById(int id) throws SongNotFoundException;
	int create(SongDTO songDto) throws SongConstraintException;
	int update(SongDTO songDto) throws SongConstraintException;
	int deleteById(int id) throws SongNotFoundException;
	List<SongDTO> findFavsSongsForUser(int id) throws SongNotFoundException, UserNumberViewsNotFoundException;
	int updateNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException;
	int insertNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException;
	Integer selectNumberViews(int idUser, int idSong) throws UserNumberViewsNotFoundException;
	
}
