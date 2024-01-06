package br.com.challenge.exceptions;

import java.io.Serial;

public class InvalidAmountException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = -4758972854410549689L;
	
	public InvalidAmountException(final String message) {
		super(message);
	}
}
