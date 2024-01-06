package br.com.challenge.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifierResponse {
	
	private boolean message;
	
	public boolean isSuccess() {
		return Boolean.TRUE.equals(message);
	}
	
	public boolean isNotSuccess() {
		return !isSuccess();
	}
}
