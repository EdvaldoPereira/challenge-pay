package br.com.challenge.controllers;

import br.com.challenge.api.ClientMapper;
import br.com.challenge.api.ClientRequest;
import br.com.challenge.api.ClientResponse;
import br.com.challenge.domains.Client;
import br.com.challenge.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private final ClientMapper clientMapper;
	private final ClientService clientService;
	
	ClientController(final ClientMapper clientMapper, final ClientService clientService) {
		this.clientMapper = clientMapper;
		this.clientService = clientService;
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> createClient(@Validated @RequestBody final ClientRequest request) {
		
		final Client client = clientMapper.clientRequestToClient(request);
		clientService.createClient(client);
		final ClientResponse clientResponse = clientMapper.clientToClientResponse(client);
		
		return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{clientIdentifier}")
	public ResponseEntity<ClientResponse> getClient(@PathVariable UUID clientIdentifier) {
		
		final Client client = clientService.getClient(clientIdentifier);
		final ClientResponse clientResponse = clientMapper.clientToClientResponse(client);
		return new ResponseEntity<>(clientResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientResponse>> getClients() {
		
		final List<Client> allClients = clientService.getAllClients();
		final List<ClientResponse> clientResponses = allClients.stream()
		                                                       .map(clientMapper::clientToClientResponse)
		                                                       .toList();
		
		return new ResponseEntity<>(clientResponses, HttpStatus.CREATED);
	}
}
