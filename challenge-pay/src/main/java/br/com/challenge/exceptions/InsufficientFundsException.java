package br.com.challenge.exceptions;

import java.io.Serial;

public class InsufficientFundsException extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 4656326210071102440L;
	
	public InsufficientFundsException(final String message) {
		super(message);
	}
}
