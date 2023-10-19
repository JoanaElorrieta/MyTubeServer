package com.reto1.myTube.service.user;

import com.reto1.myTube.model.user.UserDTO;

public interface UserService {

	UserDTO findByEmail(String email);
	int create(UserDTO userDto);
	int update(UserDTO userDto);
	
}
