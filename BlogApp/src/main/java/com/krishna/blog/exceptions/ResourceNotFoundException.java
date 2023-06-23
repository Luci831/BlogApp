package com.krishna.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourecName;
	String fieldName;
	long fieldValue;

	public ResourceNotFoundException(String resourecName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s:%s",resourecName,fieldName,fieldValue));
		this.resourecName = resourecName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
