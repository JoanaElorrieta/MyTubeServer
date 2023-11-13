package com.reto1.myTube.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "UserNumberViews constraint error")
public class UserNumberViewsConstraintException extends Exception{
	private static final long serialVersionUID = 1L;

	public UserNumberViewsConstraintException(String message) {
		super(message);
	}
}
