package com.matcodem.nestaider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({TaskNotFoundException.class, TaskAlreadyCompletedException.class})
	public ResponseEntity<String> handleTaskExceptions(Exception e) {
		log.error("Exception: ", e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(EventPublishingException.class)
	public ResponseEntity<String> handleEventPublishingException(EventPublishingException e) {
		log.error("Exception: ", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
		log.error("Exception: ", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request contains null parameters");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception e) {
		log.error("Exception: ", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	}
}
