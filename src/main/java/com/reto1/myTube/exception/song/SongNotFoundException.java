package com.reto1.myTube.exception.song;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "SongNumberViews not found error")
public class SongNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public SongNotFoundException(String message) {
		super(message);
	}
}
