package com.reto1.myTube.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reto1.myTube.exception.user.FavoriteUserSongConstraintException;
import com.reto1.myTube.exception.user.FavoriteUserSongNotFoundException;
import com.reto1.myTube.exception.user.UserNotFoundConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.user.UserDAO;
import com.reto1.myTube.model.user.UserDTO;
import com.reto1.myTube.repository.user.UserRepository;
import com.reto1.myTube.service.song.SongService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	SongService songService;

	@Override
	public UserDTO loadUser(String email) throws UserNumberViewsNotFoundException {

		return userDAOtoUserDTO(userRepository.loadUser(email));

	}

	@Override
	public int create(UserDTO userDto) throws UserNotFoundConstraintException {
		return userRepository.create(userDTOtoUserDAO(userDto));
	}

	@Override
	public int update(String email, String password) throws UserNotFoundConstraintException {
		return userRepository.update(email, password);
	}


	@Override
	public int createFavSong(int idUser, int idSong) throws FavoriteUserSongConstraintException {
		return userRepository.createFavSong(idUser, idSong);
	}

	@Override
	public int deleteFavSong(int idUser, int idSong) throws FavoriteUserSongNotFoundException {
		return userRepository.deleteFavSong(idUser, idSong);
	}



	@Override
	public List<Integer> getNumberViews(int idUser) throws UserNumberViewsNotFoundException {
		return userRepository.getNumberViews(idUser);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			return userRepository.findByEmail(email)
				.orElseThrow(
						() -> new UsernameNotFoundException("User " + email + " not found"));
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Integrity fail when try to found a user");
		} catch (UserNotFoundConstraintException e) {
			throw new UsernameNotFoundException("Integrity fail when try to found a user");
		}

	}

	//CONVERSIONES

	private UserDTO userDAOtoUserDTO(UserDAO userDao) {

		return new UserDTO(
				userDao.getId(),
				userDao.getName(),
				userDao.getLastName(),
				userDao.getEmail(),
				userDao.getPassword()
				); 

	}

	private UserDAO userDTOtoUserDAO(UserDTO userDto) {

		return new UserDAO(
				userDto.getId(),
				userDto.getName(),
				userDto.getLastName(),
				userDto.getEmail(),
				userDto.getPassword()
				); 

	}

}
