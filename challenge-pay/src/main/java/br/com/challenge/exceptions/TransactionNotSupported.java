package br.com.challenge.exceptions;

import java.io.Serial;

public class TransactionNotSupported extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 8344633547475055149L;
	
	public TransactionNotSupported (final String message) {
		super(message);
	}
}
