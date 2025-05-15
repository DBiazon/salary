package com.salary.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2540277252537055612L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}