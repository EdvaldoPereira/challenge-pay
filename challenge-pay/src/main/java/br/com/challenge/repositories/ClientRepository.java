package br.com.challenge.repositories;

import br.com.challenge.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Optional<Client> getClientByClientIdentifier(final UUID clientIdentifier);
}
