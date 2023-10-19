package com.reto1.myTube.controller;

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

import com.reto1.myTube.model.user.UserDTO;
import com.reto1.myTube.model.user.UserFavsSongGetRequest;
import com.reto1.myTube.model.user.UserPostRequest;
import com.reto1.myTube.service.user.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users/{email}")
	public ResponseEntity<UserFavsSongGetRequest> getUserByEmail(@PathVariable("email") String email) {
		UserFavsSongGetRequest userFavsSongGetRequest = userDTOtoUserFavsSongGetRequest(userService.findByEmail(email));
		return new ResponseEntity<>(userFavsSongGetRequest, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody UserPostRequest userPostRequest) {
		userService.create(userPostRequestToUserDTO(userPostRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/users/{email}")
	public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestBody UserPostRequest userPostRequest) {
		userPostRequest.setEmail(email);
		userService.update(userPostRequestToUserDTO(userPostRequest));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/users/{idUser},{idSong}/favorite")
	public ResponseEntity<?> createFavSongForUser(@PathVariable("idUser") int idUser, @PathVariable("idSong") int idSong) {
		userService.createFavSong(idUser, idSong);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{idUser},{idSong}/favorite")
	public ResponseEntity<?> deleteFavSongForUser(@PathVariable("idUser") int idUser, @PathVariable("idSong") int idSong) {
		userService.deleteFavSong(idUser, idSong);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	//CONVERSIONES

	private UserFavsSongGetRequest userDTOtoUserFavsSongGetRequest(UserDTO userDto) {

		return new UserFavsSongGetRequest(
				userDto.getId(),
				userDto.getName(),
				userDto.getLastName(),
				userDto.getEmail(),
				userDto.getPassword(),
				userDto.getListSongFavs()
				); 

	}

	private UserDTO userPostRequestToUserDTO(UserPostRequest userPostRequest) {

		return new UserDTO(
				userPostRequest.getId(),
				userPostRequest.getName(),
				userPostRequest.getLastName(),
				userPostRequest.getEmail(),
				userPostRequest.getPassword()
				); 

	}


}
