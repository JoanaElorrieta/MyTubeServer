package com.reto1.myTube.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "User constraint error")
public class UserNotFoundConstraintException extends Exception {
	private static final long serialVersionUID = 1L;
		
	public UserNotFoundConstraintException(String message) {
		super(message);
	}
}
