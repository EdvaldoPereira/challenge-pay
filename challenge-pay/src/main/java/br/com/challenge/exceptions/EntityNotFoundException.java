package br.com.challenge.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 4145771841079164089L;
	
	public EntityNotFoundException(final String message) {
		super(message);
	}
}
