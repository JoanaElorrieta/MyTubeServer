package com.reto1.myTube.service.user;

import java.util.List;

import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.user.UserDTO;

public interface UserService {

	UserDTO findByEmail(String email, String password);
	int create(UserDTO userDto);
	int update(String email, String password);
	List<SongDTO> findFavsSongsForUser(int id);
	int createFavSong(int idUser, int idSong);
	int deleteFavSong(int idUser, int idSong);
	int updateNumberViews(int idUser, int idSong);
	List<Integer> getNumberViews(int idUser);
	
}
