package com.reactive.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Integer statusCode;
	private final String message;

}
