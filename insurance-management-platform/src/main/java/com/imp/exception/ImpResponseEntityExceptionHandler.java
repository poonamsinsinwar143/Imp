package com.imp.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ImpResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ImpResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(value = { ImpBusinessException.class })
	protected ResponseEntity<Object> handleException(ImpBusinessException ex, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}