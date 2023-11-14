package com.reto1.myTube.service.user;

import java.util.List;

import com.reto1.myTube.exception.user.FavoriteUserSongConstraintException;
import com.reto1.myTube.exception.user.FavoriteUserSongNotFoundException;
import com.reto1.myTube.exception.user.PasswordNotMatchesException;
import com.reto1.myTube.exception.user.UserNotFoundConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.user.UserDTO;

public interface UserService {

	UserDTO loadUser(String email) throws UserNumberViewsNotFoundException;
	int create(UserDTO userDto) throws UserNotFoundConstraintException;
	int update(String email, String oldPassword, String password) throws UserNotFoundConstraintException, UserNumberViewsNotFoundException, PasswordNotMatchesException;
	int createFavSong(int idUser, int idSong) throws FavoriteUserSongConstraintException;
	int deleteFavSong(int idUser, int idSong) throws FavoriteUserSongNotFoundException;
	List<Integer> getNumberViews(int idUser) throws UserNumberViewsNotFoundException;
	
}
