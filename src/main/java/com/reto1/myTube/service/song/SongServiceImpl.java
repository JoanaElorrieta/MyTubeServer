package com.reto1.myTube.service.song;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto1.myTube.model.song.SongDAO;
import com.reto1.myTube.model.song.SongDTO;
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

	//Conversiones
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

	private SongDAO songDTOtoSongDAO(SongDTO songDto) {

		return new SongDAO(
				songDto.getId(), 
				songDto.getTitle(),
				songDto.getAuthor(),
				songDto.getUrl()
				); 

	}

}
