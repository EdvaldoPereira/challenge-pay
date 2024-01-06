package br.com.challenge.services;

import br.com.challenge.domains.Client;
import br.com.challenge.exceptions.ResourceDataIntegrityViolationException;
import br.com.challenge.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	public ClientService(final ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public void createClient(final Client client) {
		try {
			this.clientRepository.save(client);
		} catch (DataIntegrityViolationException exception) {
			throw new ResourceDataIntegrityViolationException("Client already exists");
		}
	}
	
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	
	public Client getClient(final UUID clientId) {
		return clientRepository.getClientByClientIdentifier(clientId).orElseThrow(
				() -> new EntityNotFoundException(" Client Identifier " + clientId + " Not found"));
	}
}
