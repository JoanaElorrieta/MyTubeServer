package com.reto1.myTube.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.user.UserDAO;
import com.reto1.myTube.model.user.UserDTO;
import com.reto1.myTube.repository.user.UserRepository;
import com.reto1.myTube.service.song.SongService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	SongService songService;
	
	@Override
	public UserDTO findByEmail(String email, String password) {
		
		UserDTO response = userDAOtoUserDTO(userRepository.findByEmail(email, password));
		
		List<SongDTO> favsSongs = findFavsSongsForUser(response.getId());
		
		List<Integer> views= getNumberViews(response.getId());
		
		response.setListSongFavs(favsSongs);
		
		response.setViews(views);
		
		return response;
	}

	@Override
	public int create(UserDTO userDto) {
		return userRepository.create(userDTOtoUserDAO(userDto));
	}

	@Override
	public int update(UserDTO userDto) {
		return userRepository.update(userDTOtoUserDAO(userDto));
	}

	@Override
	public List<SongDTO> findFavsSongsForUser(int id) {
		return songService.findFavsSongsForUser(id);
	}
	
	@Override
	public int createFavSong(int idUser, int idSong) {
		return userRepository.createFavSong(idUser, idSong);
	}
	
	@Override
	public int deleteFavSong(int idUser, int idSong) {
		return userRepository.deleteFavSong(idUser, idSong);
	}
	
	@Override
	public int updateNumberViews(int idUser, int idSong) {
		return userRepository.updateNumberViews(idUser, idSong);
	}
	
	@Override
	public List<Integer> getNumberViews(int idUser) {
		return userRepository.getNumberViews(idUser);
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
