package com.sample.app.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sample.app.dto.ErrorResponse;



@ControllerAdvice
public class FlightSearchExceptionHandler {
	
	/**
	 * Handler for validation Exception
	 * @param exception
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(ConstraintViolationException exception,
			WebRequest request) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * handleFlightsNotException
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FlightsNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFlightsNotException(ConstraintViolationException exception,
			WebRequest request) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * generic handler for all exceptions
	 * @param exception
	 * @param request
	 * @return ErrorResponse
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(ConstraintViolationException exception,
			WebRequest request) {
		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}