package com.salary.exceptions;

public class ResourceAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1133569841649172552L;

	public ResourceAlreadyRegisteredException(String msg) {
		super(msg);
	}
	
	

}