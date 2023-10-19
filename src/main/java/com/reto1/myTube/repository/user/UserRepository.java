package com.reto1.myTube.repository.user;

import com.reto1.myTube.model.user.UserDAO;

public interface UserRepository {

	UserDAO findByEmail(String email);
	int create(UserDAO userDao);
	int update(UserDAO userDao);
	
}
