package com.techhub.demo.Exceptions;

import lombok.Data;

@Data
public class ErrorMessage {

	private int statusCode;
	private String message;
	
	public ErrorMessage(int i, String message)
	{	this.statusCode=i;
		this.message=message;
	}

}
