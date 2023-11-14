package com.reto1.myTube.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.reto1.myTube.exception.user.FavoriteUserSongConstraintException;
import com.reto1.myTube.exception.user.FavoriteUserSongNotFoundException;
import com.reto1.myTube.exception.user.UserNotFoundConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.user.UserDAO;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Optional<UserDAO> findByEmail(String email) throws UserNotFoundConstraintException {
		try {
			UserDAO user = jdbcTemplate.queryForObject("SELECT * from user where email = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e){
			throw new UserNotFoundConstraintException("Integrity fail when try to get a user");
		}
	}

	@Override
	public int create(UserDAO userDao) throws UserNotFoundConstraintException {
		try {	
			System.out.println(userDao.getEmail());
			return jdbcTemplate.update(
					"INSERT INTO user ( name, lastName, email, password) VALUES(?, ?, ?, ?)",
					new Object[] { userDao.getName(), userDao.getLastName(), userDao.getEmail(), userDao.getPassword() }
					);
		}catch(DataIntegrityViolationException e) {
			throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
		}
	}

	@Override
	public int update(String email, String password) throws UserNotFoundConstraintException {
		try {
			return jdbcTemplate.update(
					"UPDATE user SET password = ? WHERE email = ?",
					new Object[] { password, email }
					);
		}catch (DataIntegrityViolationException e) {
			throw new UserNotFoundConstraintException("Integrity fail when try to update a user");
		}
	}

	@Override
	public int createFavSong(int idUser, int idSong) throws FavoriteUserSongConstraintException {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO favorite ( id_user, id_song ) VALUES(?, ?)",
					new Object[] { idUser, idSong }
					);
		}catch(DataIntegrityViolationException e) {
			throw new FavoriteUserSongConstraintException("Integrity fail when try to create a favorite song");
		}
	}
	
	@Override
	public int deleteFavSong(int idUser, int idSong) throws FavoriteUserSongNotFoundException {	
		int rowsAffected = jdbcTemplate.update(
				"DELETE FROM favorite WHERE id_user = ? AND id_song = ?",
				new Object[] { idUser, idSong }
				);
		if (rowsAffected != 0) {
			return rowsAffected;
		}else {
		throw new FavoriteUserSongNotFoundException("Integrity fail when try to delete a favorite song");
		}
	}

	public List<Integer> getNumberViews(int idUser) throws UserNumberViewsNotFoundException {
	    try {
	        List<Integer> result = jdbcTemplate.queryForList(
	            "SELECT views FROM play WHERE id_user = ?",
	            Integer.class,
	            idUser
	        );
	        return result;
	    } catch (EmptyResultDataAccessException e) {
	    	throw new UserNumberViewsNotFoundException("Integrity fail when try to get the views from user");
	    }
	}

	@Override
	public UserDAO loadUser(String email) throws UsernameNotFoundException {
		try {
			UserDAO user = jdbcTemplate.queryForObject("SELECT * from user where email = ?", BeanPropertyRowMapper.newInstance(UserDAO.class), email);
			return (user);
		} catch (UsernameNotFoundException e){
			throw new UsernameNotFoundException("Integrity fail when try to found a user");
		}
	}

}
