package com.reactive.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;

import com.reactive.response.Response;

import jakarta.validation.ConstraintViolationException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = AppException.class)
	public Mono<ResponseEntity<Response>> appExceptionHandler(AppException e, WebRequest w) {
	    HttpStatus status = HttpStatusCode.getHttpStatusByCode(e.getStatusCode());
	    Response response = Response.builder().statusCode(e.getStatusCode()).statusMessage(e.getMessage()).build();
	    return Mono.just(ResponseEntity.status(status).body(response));
	}


	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {
		Response response = Response.builder().statusCode(400).statusMessage(ex.getBindingResult().getFieldErrors()
				.stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "))).build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	protected ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		Response response = Response.builder().statusCode(400).statusMessage(ex.getConstraintViolations().stream()
				.map(violation -> violation.getMessage()).collect(Collectors.joining(", "))).build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WebExchangeBindException.class)
	@ResponseStatus(value =HttpStatus.BAD_REQUEST )
	public Mono<Response> handlerBindException(WebExchangeBindException e) {
		Response response = Response.builder().statusCode(400)
				.statusMessage(e.getFieldError() != null ? e.getFieldError().getDefaultMessage() : "Invalid input")
				.build();
		return Mono.just(response);
	}
}
