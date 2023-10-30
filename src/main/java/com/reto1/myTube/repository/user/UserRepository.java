package com.reto1.myTube.repository.user;

import java.util.List;

import com.reto1.myTube.model.user.UserDAO;

public interface UserRepository {

	UserDAO findByEmail(String email, String password);
	int create(UserDAO userDao);
	int update(String email, String password);
	int createFavSong(int idUser, int idSong);
	int deleteFavSong(int idUser, int idSong);
	int updateNumberViews(int idUser, int idSong);
	List<Integer> getNumberViews(int idUser);
}
