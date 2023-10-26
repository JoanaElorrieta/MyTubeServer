package com.reto1.myTube.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reto1.myTube.model.user.UserDAO;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDAO findByEmail(String email, String password) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM user where email = ? and password = ?",
					BeanPropertyRowMapper.newInstance(UserDAO.class),
					email, password
					);}
		catch (Exception e) {
			//TODO exceptions
			//throw new UserNotFoundException("User not found in DataBase");
			return null;
		}
	}

	@Override
	public int create(UserDAO userDao) {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO user ( name, lastName, email, password) VALUES(?, ?, ?, ?)",
					new Object[] { userDao.getName(), userDao.getLastName(), userDao.getEmail(), userDao.getPassword() }
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}

	@Override
	public int update(UserDAO userDao) {
		try {

			return jdbcTemplate.update(
					"UPDATE user SET password = ? WHERE email = ?",
					new Object[] { userDao.getPassword(), userDao.getEmail() }
					);
		}catch (DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to update a user");
			return 0;
		}
	}

	@Override
	public int createFavSong(int idUser, int idSong) {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO favorite ( id_user, id_song ) VALUES(?, ?)",
					new Object[] { idUser, idSong }
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}
	
	@Override
	public int deleteFavSong(int idUser, int idSong) {
		try {	
			return jdbcTemplate.update(
					"DELETE FROM favorite WHERE id_user = ? AND id_song = ?",
					new Object[] { idUser, idSong }
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}

}
