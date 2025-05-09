package com.techhub.demo.Exceptions;

public class UserNotFoundException extends RuntimeException {

	private String massage;

	public UserNotFoundException(String massage) {
		super(massage);
		this.massage = massage;
	}

}
