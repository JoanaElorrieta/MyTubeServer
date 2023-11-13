package com.reto1.myTube.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "UserNumberViews not found error")
public class UserNumberViewsNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNumberViewsNotFoundException(String message) {
		super(message);
	}
}
