package com.reto1.myTube.repository.song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reto1.myTube.exception.song.SongConstraintException;
import com.reto1.myTube.exception.song.SongNotFoundException;
import com.reto1.myTube.exception.user.UserNumberViewsConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.song.SongDAO;

@Repository
public class SongRepositoryImpl implements SongRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SongDAO> findAll() throws SongNotFoundException {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM song",
					BeanPropertyRowMapper.newInstance(SongDAO.class));
		}catch (EmptyResultDataAccessException e) {
			throw new SongNotFoundException("Integrity fail song not found");
		}
	}

	@Override
	public SongDAO findById(int idSong) throws SongNotFoundException {

		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM song where ID = ?",
					BeanPropertyRowMapper.newInstance(SongDAO.class),
					idSong
					);}
		catch (EmptyResultDataAccessException e) {
			throw new SongNotFoundException("Integrity fail song not found");
		}
	}

	@Override
	public int create(SongDAO songDao) throws SongConstraintException {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO song ( title, author, url ) VALUES(?, ?, ?)",
					new Object[] { songDao.getTitle(), songDao.getAuthor(), songDao.getUrl()}
					);
		}catch(DataIntegrityViolationException e) {
			throw new SongConstraintException("Integrity fail when try to create a song");
		}
	}

	@Override
	public int update(SongDAO songDao) throws SongConstraintException {
		try {
			return jdbcTemplate.update(
					"UPDATE song SET title = ?, author = ?, url = ? WHERE id = ?",
					new Object[] { songDao.getTitle(), songDao.getAuthor() ,songDao.getUrl(), songDao.getId()}
					);
		}catch (DataIntegrityViolationException e) {
			throw new SongConstraintException("Integrity fail when try to update a song");
		}
	}

	@Override
	public int deleteById(int idSong) throws SongNotFoundException {
		int rowsAffected = jdbcTemplate.update(
				"DELETE FROM song WHERE id = ?", 
				idSong
				);
		if (rowsAffected != 0) {
			return rowsAffected;
		}else {
			throw new SongNotFoundException("Integrity fail when try to delete a song");
		}
	}

	@Override
	public Integer getFavsSongsForCertainUser(int idUser, int idSong) throws SongNotFoundException {
		try {
		return jdbcTemplate.queryForObject(
				"SELECT CASE WHEN f.id_song IS NOT NULL THEN 1 ELSE 0 END AS is_favorite FROM song s LEFT JOIN favorite f ON s.id = f.id_song AND f.id_user = ? WHERE s.id = ?;",
				Integer.class,
				idUser, idSong
				);
		 } catch (EmptyResultDataAccessException e) {
			 throw new SongNotFoundException("Integrity fail when try to get Favorites a song");
		 }
	}
	
	@Override
	public int updateNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException {
		try {	
			return jdbcTemplate.update(
					"UPDATE play SET views=views+1 WHERE id_user = ? AND id_song = ?",
					new Object[] { idUser, idSong }
					);
		}catch(DataIntegrityViolationException e) {
			throw new UserNumberViewsConstraintException("Integrity fail when try to update a number of view for user");
		}
	}
	
	@Override
	public int insertNumberViews(int idUser, int idSong) throws UserNumberViewsConstraintException {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO play ( id_song, id_user, views ) VALUES(?, ?, 1)",
					new Object[] { idSong, idUser}
					);
		}catch(DataIntegrityViolationException e) {
			throw new UserNumberViewsConstraintException("Integrity fail when try to create a number of view for user");
		}
	}
	@Override
	public Integer selectNumberViews(int idUser, int idSong) throws UserNumberViewsNotFoundException {
	    try {
	        return jdbcTemplate.queryForObject(
	                "SELECT COALESCE(play.views, 0) AS views FROM song LEFT JOIN play ON song.id = play.id_song AND play.id_user = ? where id=?",
	                Integer.class,
	                idUser,idSong
	        );
	    } catch (EmptyResultDataAccessException e) {
	    	throw new UserNumberViewsNotFoundException("Integrity fail when try to get the views from user"); 
	    }
	}

}
