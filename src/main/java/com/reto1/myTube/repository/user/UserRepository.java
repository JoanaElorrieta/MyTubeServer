package com.reto1.myTube.repository.user;

import java.util.List;
import java.util.Optional;

import com.reto1.myTube.model.user.UserDAO;

public interface UserRepository {

	Optional<UserDAO> findByEmail(String email);
	UserDAO loadUser(String email);
	int create(UserDAO userDao);
	int update(String email, String password);
	int createFavSong(int idUser, int idSong);
	int deleteFavSong(int idUser, int idSong);
	List<Integer> getNumberViews(int idUser);
}
