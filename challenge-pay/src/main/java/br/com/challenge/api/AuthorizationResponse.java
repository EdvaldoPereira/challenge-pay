package br.com.challenge.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizationResponse {
	
	private String message;
	
	public boolean isApproved() {
		
		final String MESSAGE_RESPONSE_APPROVED = "Autorizado";
		return MESSAGE_RESPONSE_APPROVED.equals(this.message);
	}
	
	public boolean isNotApproved() {
		return !isApproved();
	}
}
