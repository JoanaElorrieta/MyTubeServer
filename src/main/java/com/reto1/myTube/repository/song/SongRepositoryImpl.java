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
	public List<SongDAO> getFavsSongsForCertainUser(int id) {
		return jdbcTemplate.query(
				"SELECT song.id, song.title, song.author, song.url FROM song\r\n"
						+ "JOIN favorite on song.id = favorite.id_song\r\n"
						+ "WHERE favorite.id_user = ?",
						BeanPropertyRowMapper.newInstance(SongDAO.class),
						id
				);
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
					"INSERT INTO song ( id_song, id_user, views ) VALUES(?, ?, 1)",
					new Object[] { idSong, idUser}
					);
		}catch(DataIntegrityViolationException e) {
			//TODO exceptions
			//throw new UserNotFoundConstraintException("Integrity fail when try to create a user");
			return 0;
		}
	}
	@Override
	public int selectNumberViews(int idUser, int idSong) {
		  try {
		        List<Integer> views = jdbcTemplate.queryForList(
		            "SELECT views FROM play WHERE id_user = ? and id_song= ?",
		            Integer.class,
		            idUser,idSong
		        );
		        if (!views.isEmpty()) {
		            int result = views.get(0);
		            return result;
		        } else {
		            return 0; // Devuelve 0 si no se encontraron registros
		        }
		    } catch (DataAccessException e) {

		        return 0;
		    }
	}


}
