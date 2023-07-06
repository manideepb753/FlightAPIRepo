package com.sample.app.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public ValidationException() {
		super();
	}

	public ValidationException(final String message) {
		super(message);
	}
}	