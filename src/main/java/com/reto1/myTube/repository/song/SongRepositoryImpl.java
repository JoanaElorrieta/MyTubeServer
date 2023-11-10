package com.reto1.myTube.repository.song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reto1.myTube.model.song.SongDAO;

@Repository
public class SongRepositoryImpl implements SongRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SongDAO> findAll() {

		try {
			return jdbcTemplate.query(
					"SELECT * FROM song",
					BeanPropertyRowMapper.newInstance(SongDAO.class));

		}catch (Exception e) {
			return null;
		}

	}

	@Override
	public SongDAO findById(int id) {

		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM song where ID = ?",
					BeanPropertyRowMapper.newInstance(SongDAO.class),
					id
					);}
		catch (Exception e) {
			//TODO exceptions
			//throw new SongNotFoundException("Song not found in DataBase");
			return null;
		}

	}


	@Override
	public int create(SongDAO songDao) {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO song ( title, author, url ) VALUES(?, ?, ?)",
					new Object[] { songDao.getTitle(), songDao.getAuthor(), songDao.getUrl()}
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new SongNotFoundConstraintException("Integrity fail when try to create a song");
			return 0;
		}
	}

	@Override
	public int update(SongDAO songDao) {
		try {

			return jdbcTemplate.update(
					"UPDATE song SET title = ?, author = ?, url = ? WHERE id = ?",
					new Object[] { songDao.getTitle(), songDao.getAuthor() ,songDao.getUrl(), songDao.getId()}
					);
		}catch (DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new SongNotFoundConstraintException("Integrity fail when try to update a song");
			return 0;
		}
	}

	@Override
	public int deleteById(int id) {
		int rowsAffected = jdbcTemplate.update(
				"DELETE FROM song WHERE id = ?", 
				id
				);

		if (rowsAffected != 0) {
			return rowsAffected;
		}else {
			//TODO exceptions
			//throw new EmployeeNotFoundException("Employee not found in DataBase");
			return 0;
		}
	}

	@Override
	public Integer getFavsSongsForCertainUser(int id, int idSong) {
		try {
		return jdbcTemplate.queryForObject(
				"SELECT CASE WHEN f.id_song IS NOT NULL THEN 1 ELSE 0 END AS is_favorite FROM song s LEFT JOIN favorite f ON s.id = f.id_song AND f.id_user = ? WHERE s.id = ?;",
				Integer.class,
				id, idSong
				);
		 } catch (DataAccessException e) {
		        return 0; 
		    }
	}
	@Override
	public int updateNumberViews(int idUser, int idSong) {
		try {	
			return jdbcTemplate.update(
					"UPDATE play SET views=views+1 WHERE id_user = ? AND id_song = ?",
					new Object[] { idUser, idSong }
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}
	@Override
	public int insertNumberViews(int idUser, int idSong) {
		try {	
			return jdbcTemplate.update(
					"INSERT INTO play ( id_song, id_user, views ) VALUES(?, ?, 1)",
					new Object[] { idSong, idUser}
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}
	@Override
	public Integer selectNumberViews(int idUser, int idSong) {
	    try {
	        return jdbcTemplate.queryForObject(
	                "SELECT COALESCE(play.views, 0) AS views FROM song LEFT JOIN play ON song.id = play.id_song AND play.id_user = ? where id=?",
	                Integer.class,
	                idUser,idSong
	        );

	    } catch (DataAccessException e) {
	        return 0; 
	    }
	}


}
