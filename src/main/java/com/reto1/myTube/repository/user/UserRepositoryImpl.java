package com.reto1.myTube.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reto1.myTube.model.user.UserDAO;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Optional<UserDAO> findByEmail(String email) {
		try {
			UserDAO user = jdbcTemplate.queryForObject("SELECT * from user where email = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public int create(UserDAO userDao) {
		try {	
			System.out.println(userDao.getEmail());
			return jdbcTemplate.update(
					"INSERT INTO user ( name, lastName, email, password) VALUES(?, ?, ?, ?)",
					new Object[] { userDao.getName(), userDao.getLastName(), userDao.getEmail(), userDao.getPassword() }
					);
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
//			TODO exceptions
//			throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}

	@Override
	public int update(String email, String password) {
		try {

			return jdbcTemplate.update(
					"UPDATE user SET password = ? WHERE email = ?",
					new Object[] { password, email }
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

	

	public List<Integer> getNumberViews(int idUser) {
	    try {
	        List<Integer> result = jdbcTemplate.queryForList(
	            "SELECT views FROM play WHERE id_user = ?",
	            Integer.class,
	            idUser
	        );
	        return result;
	    } catch (DataAccessException e) {
	        // Manejar excepciones aquí
	        e.printStackTrace(); // O manejar la excepción de alguna otra forma
	        return null;
	    }
	}

	@Override
	public UserDAO loadUser(String email) {
		try {
			UserDAO user = jdbcTemplate.queryForObject("SELECT * from user where email = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), email);
			return (user);
		} catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

}
