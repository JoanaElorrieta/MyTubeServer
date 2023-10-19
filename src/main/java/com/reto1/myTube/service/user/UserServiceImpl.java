package com.reto1.myTube.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto1.myTube.model.user.UserDAO;
import com.reto1.myTube.model.user.UserDTO;
import com.reto1.myTube.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO findByEmail(String email) {
		return userDAOtoUserDTO(userRepository.findByEmail(email));
	}

	@Override
	public int create(UserDTO userDto) {
		return userRepository.create(userDTOtoUserDAO(userDto));
	}

	@Override
	public int update(UserDTO userDto) {
		return userRepository.update(userDTOtoUserDAO(userDto));
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
