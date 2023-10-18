package com.reto1.myTube.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reto1.myTube.model.song.SongDTO;
import com.reto1.myTube.model.song.SongGetRequest;
import com.reto1.myTube.model.song.SongPostRequest;
import com.reto1.myTube.service.song.SongService;

@RestController
public class SongController {

	@Autowired
	SongService songService;

	@GetMapping("/songs")
	public ResponseEntity<List<SongGetRequest>> getSong() {
		return new ResponseEntity<>(songDtoListToSongGetRequestList(songService.findAll()), HttpStatus.OK);
	}

	@GetMapping("/songs/{id}")
	public ResponseEntity<SongGetRequest> getSongById(@PathVariable("id") int id) {
		SongGetRequest songGetRequest = songDTOToSongGetRequest(songService.findById(id));
		return new ResponseEntity<>(songGetRequest, HttpStatus.OK);
	}

	@PostMapping("/songs")
	public ResponseEntity<?> createSong(@RequestBody SongPostRequest songPostRequest) {
		songService.create(songPostRequestToSongDTO(songPostRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@PutMapping("/songs/{id}")
	public ResponseEntity<?> updateSong(@PathVariable("id") int id, @RequestBody SongPostRequest songPostRequest) {
		songPostRequest.setId(id);
		songService.update(songPostRequestToSongDTO(songPostRequest));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/songs/{id}")
	public ResponseEntity<?> deleteSong(@PathVariable("id") int id) {
		songService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
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
