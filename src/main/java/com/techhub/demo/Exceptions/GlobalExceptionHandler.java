package com.techhub.demo.Exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleUserException(UserNotFoundException exception) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

}
