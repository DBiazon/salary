package com.salary.model.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Getter
public class ValidationError extends CustomError {

	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public void addError(String fildName, String message) {
		errors.add(new FieldMessage(fildName, message));
	}

}