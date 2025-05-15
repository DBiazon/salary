package com.salary.exceptions;

public class ResourceActiveException extends RuntimeException {

	private static final long serialVersionUID = 6020353061463604894L;

	public ResourceActiveException(String msg) {
		super(msg);
	}
}