package com.reto1.myTube.repository.user;

import java.util.List;
import java.util.Optional;

import com.reto1.myTube.exception.user.FavoriteUserSongConstraintException;
import com.reto1.myTube.exception.user.FavoriteUserSongNotFoundException;
import com.reto1.myTube.exception.user.UserNotFoundConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.user.UserDAO;

public interface UserRepository {

	Optional<UserDAO> findByEmail(String email) throws UserNotFoundConstraintException;
	UserDAO loadUser(String email) throws UserNumberViewsNotFoundException;
	int create(UserDAO userDao) throws UserNotFoundConstraintException;
	int update(String email, String password) throws UserNotFoundConstraintException;
	int createFavSong(int idUser, int idSong) throws FavoriteUserSongConstraintException;
	int deleteFavSong(int idUser, int idSong) throws FavoriteUserSongNotFoundException;
	List<Integer> getNumberViews(int idUser) throws UserNumberViewsNotFoundException;
}
