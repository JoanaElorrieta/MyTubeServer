package com.reto1.myTube.repository.song;

import java.util.List;

import com.reto1.myTube.exception.song.SongConstraintException;
import com.reto1.myTube.exception.song.SongNotFoundException;
import com.reto1.myTube.exception.user.UserNumberViewsConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.song.SongDAO;

public interface SongRepository {

	List<SongDAO> findAll() throws SongNotFoundException;
	SongDAO findById(int id) throws SongNotFoundException;
	int create(SongDAO songDao) throws SongConstraintException;
	int update(SongDAO songDao) throws SongConstraintException;
	int deleteById(int id) throws SongNotFoundException;
	Integer getFavsSongsForCertainUser(int id, int idSong) throws SongNotFoundException;
	int updateNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException;
	int insertNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException;
	Integer selectNumberViews(int idUser, int idSong) throws UserNumberViewsNotFoundException;
	
}
