package com.sample.app.exception;

public class FlightsNotFoundException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public FlightsNotFoundException() {
		super();
	}

	public FlightsNotFoundException(final String message) {
		super(message);
	}
}
