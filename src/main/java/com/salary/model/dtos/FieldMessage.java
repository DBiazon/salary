package com.salary.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldMessage {

	private String fieldName;
	private String message;
}