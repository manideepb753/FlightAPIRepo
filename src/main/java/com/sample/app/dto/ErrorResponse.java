package com.sample.app.dto;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private HttpStatus responseStatus;
	private String message;
}
