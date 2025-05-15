package com.salary.exceptions;

public class DatebaseException extends RuntimeException {

	private static final long serialVersionUID = 576775958778618688L;

	public DatebaseException(String msg) {
		super(msg);
	}

}