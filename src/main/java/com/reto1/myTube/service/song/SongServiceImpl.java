package com.reto1.myTube.service.song;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto1.myTube.model.song.SongDAO;
import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.song.SongFavsViewsRequest;
import com.reto1.myTube.repository.song.SongRepository;

@Service
public class SongServiceImpl implements SongService{

	@Autowired
	SongRepository songRepository;

	@Override
	public List<SongDTO> findAll() {
		return songDAOListToSongDTOList(songRepository.findAll());
	}

	@Override
	public SongDTO findById(int id) {
		return songDAOtoSongDTO(songRepository.findById(id));
	}

	@Override
	public int create(SongDTO songDto) {
		return songRepository.create(songDTOtoSongDAO(songDto));
	}

	@Override
	public int update(SongDTO songDto) {
		return songRepository.update(songDTOtoSongDAO(songDto));
	}

	@Override
	public int deleteById(int id) {
		return songRepository.deleteById(id);
	}
	
	@Override
	public List<SongDTO> findFavsSongsForUser(int id) {
		List<SongDTO> response= songDAOtoSongFav(songRepository.findAll(), id);
		return response;
	}
	@Override
	public int updateNumberViews(int idUser, int idSong) {
		return songRepository.updateNumberViews(idUser, idSong);
	}
	@Override
	public int insertNumberViews(int idUser, int idSong) {
		return songRepository.insertNumberViews(idUser, idSong);
	}
	@Override
	public Integer selectNumberViews(int idUser, int idSong) {
		return songRepository.selectNumberViews(idUser, idSong);
	}
	//CONVERSIONES
	
	private SongDTO songDAOtoSongDTO(SongDAO songDao) {

		return new SongDTO(
				songDao.getId(), 
				songDao.getTitle(),
				songDao.getAuthor(),
				songDao.getUrl()
				); 

	}
	
	private List<SongDTO> songDAOListToSongDTOList(List<SongDAO> songDaoList) {

		List<SongDTO> songDTOList = new ArrayList<SongDTO>();

		for (SongDAO songDAO : songDaoList) {

			songDTOList.add(songDAOtoSongDTO(songDAO));

		}

		return songDTOList;

	}
	
	private List<SongDTO> songDAOtoSongFav(List<SongDAO> songDaoList, int id) {

		List<SongDTO> songFavList = new ArrayList<SongDTO>();

		for (SongDAO songDAO : songDaoList) {
			SongDTO response=songDAOtoSongDTO(songDAO);
			Integer favorite=songRepository.getFavsSongsForCertainUser(id,songDAO.getId());		
			Integer views=songRepository.selectNumberViews(id,songDAO.getId());
			response.setViews(views);
			response.setFavorite(favorite);
			songFavList.add(response);

		}

		return songFavList;

	}

	private SongDAO songDTOtoSongDAO(SongDTO songDto) {

		return new SongDAO(
				songDto.getId(), 
				songDto.getTitle(),
				songDto.getAuthor(),
				songDto.getUrl()
				); 

	}

}
