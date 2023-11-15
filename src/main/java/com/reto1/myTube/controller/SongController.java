package com.reto1.myTube.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reto1.myTube.exception.song.SongConstraintException;
import com.reto1.myTube.exception.song.SongNotFoundException;
import com.reto1.myTube.exception.user.UserNumberViewsConstraintException;
import com.reto1.myTube.exception.user.UserNumberViewsNotFoundException;
import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.song.SongFavsViewsRequest;
import com.reto1.myTube.model.song.SongGetRequest;
import com.reto1.myTube.model.song.SongPostRequest;
import com.reto1.myTube.model.user.UserDAO;
import com.reto1.myTube.service.song.SongService;

@RestController
public class SongController {

	@Autowired
	SongService songService;

	@GetMapping("/songs")
	public ResponseEntity<List<SongGetRequest>> getAllSongs() throws SongNotFoundException {
		return new ResponseEntity<>(songDtoListToSongGetRequestList(songService.findAll()), HttpStatus.OK);
	}

	@GetMapping("/songs/{id}")
	public ResponseEntity<SongGetRequest> getSongById(@PathVariable("id") int id) throws SongNotFoundException {
		SongGetRequest songGetRequest = songDTOToSongGetRequest(songService.findById(id));
		return new ResponseEntity<>(songGetRequest, HttpStatus.OK);
	}
	@GetMapping("/songs/user")
	public ResponseEntity<List<SongFavsViewsRequest>> getSongsFavoriteViews(Authentication authentication) throws SongNotFoundException, UserNumberViewsNotFoundException {
		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		List<SongFavsViewsRequest> songFavsViewsRequest = songDTOListToSongFavsViewsRequestList(songService.findFavsSongsForUser(userDetails.getId()));
		return new ResponseEntity<>(songFavsViewsRequest, HttpStatus.OK);
	}

	@PostMapping("/songs")
	public ResponseEntity<?> createSong(@RequestBody SongPostRequest songPostRequest) throws SongConstraintException {
		songService.create(songPostRequestToSongDTO(songPostRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/songs/{id}")
	public ResponseEntity<?> updateSong(@PathVariable("id") int id, @RequestBody SongPostRequest songPostRequest) throws SongConstraintException {
		songPostRequest.setId(id);
		songService.update(songPostRequestToSongDTO(songPostRequest));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/songs/{id}")
	public ResponseEntity<?> deleteSong(@PathVariable("id") int id) throws SongNotFoundException {
		songService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PutMapping("/songs/{idSong}/play")
	public ResponseEntity<?> updateNumberViews(Authentication authentication, @PathVariable("idSong") int idSong) throws UserNumberViewsConstraintException {
		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		songService.updateNumberViews(userDetails.getId(), idSong);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping("/songs/{idSong}/play")
	public ResponseEntity<?> insertNumberViews(Authentication authentication, @PathVariable("idSong") int idSong) throws UserNumberViewsConstraintException {
		UserDAO userDetails = (UserDAO) authentication.getPrincipal();
		songService.insertNumberViews(userDetails.getId(), idSong);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Conversiones

	public SongDTO songPostRequestToSongDTO(SongPostRequest songPostRequest) {

		return new SongDTO(
				songPostRequest.getId(), 
				songPostRequest.getTitle(), 
				songPostRequest.getAuthor(), 
				songPostRequest.getUrl()
				); 

	}
	public SongFavsViewsRequest songDTOToSongFavsViewsRequest(SongDTO songDTO) {

		return new SongFavsViewsRequest(
				songDTO.getId(), 
				songDTO.getTitle(), 
				songDTO.getAuthor(), 
				songDTO.getUrl(),
				songDTO.getFavorite(),
				songDTO.getViews()
				); 

	}
	public List<SongFavsViewsRequest> songDTOListToSongFavsViewsRequestList(List<SongDTO> songDtoList) {

		List<SongFavsViewsRequest> songFavsViewsRequest = new ArrayList<SongFavsViewsRequest>();

		for (SongDTO songDTO : songDtoList) {

			songFavsViewsRequest.add(songDTOToSongFavsViewsRequest(songDTO));

		}

		return songFavsViewsRequest;

	}

	public SongGetRequest songDTOToSongGetRequest(SongDTO employeeDto) {

		return new SongGetRequest(
				employeeDto.getId(), 
				employeeDto.getTitle(),
				employeeDto.getAuthor(),
				employeeDto.getUrl()
				); 

	}

	public List<SongGetRequest> songDtoListToSongGetRequestList(List<SongDTO> songDtoList) {

		List<SongGetRequest> songGetRequest = new ArrayList<SongGetRequest>();

		for (SongDTO songDTO : songDtoList) {

			songGetRequest.add(songDTOToSongGetRequest(songDTO));

		}

		return songGetRequest;

	}

}
