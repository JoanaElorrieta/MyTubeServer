package com.reto1.myTube.exception.song;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Song constraint error")
public class SongConstraintException extends Exception{
	private static final long serialVersionUID = 1L;

	public SongConstraintException(String message) {
		super(message);
	}
}
