package com.reto1.myTube.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED, reason = "Current password not matches with incoming password")
public class PasswordNotMatchesException extends Exception{
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchesException(String message) {
		super(message);
	}
	
}
