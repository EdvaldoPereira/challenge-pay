package br.com.challenge.services;

import br.com.challenge.api.AuthorizationResponse;
import br.com.challenge.api.AuthorizerClient;
import org.springframework.stereotype.Service;

@Service
public class AuthorizerService {
	
	private final AuthorizerClient authorizerClient;
	
	AuthorizerService(final AuthorizerClient authorizerClient) {
		this.authorizerClient = authorizerClient;
	}
	
	public AuthorizationResponse authorize () {
		return authorizerClient.requestAuthorization().getBody();
	}
}
