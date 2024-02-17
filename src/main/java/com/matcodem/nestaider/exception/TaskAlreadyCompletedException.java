package com.matcodem.nestaider.exception;

public class TaskAlreadyCompletedException extends RuntimeException {
	public TaskAlreadyCompletedException(String message) {
		super(message);
	}
}