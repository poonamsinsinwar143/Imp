package com.imp.exception;

import org.springframework.http.HttpStatus;

public class ImpBusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private HttpStatus httpStatus;
	
	public ImpBusinessException(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
