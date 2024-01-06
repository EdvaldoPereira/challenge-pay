package br.com.challenge.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class ResourceDataIntegrityViolationException extends DataIntegrityViolationException {
	
	@Serial
	private static final long serialVersionUID = 9044027669549811735L;
	
	public ResourceDataIntegrityViolationException(final String message) {
		super(message);
	}
}
