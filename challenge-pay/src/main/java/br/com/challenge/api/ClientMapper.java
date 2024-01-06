package br.com.challenge.api;

import br.com.challenge.domains.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "wallet", ignore = true)
	@Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "clientIdentifier", expression = "java(java.util.UUID.randomUUID())")
	Client clientRequestToClient(final ClientRequest clientRequest);
	
	ClientResponse clientToClientResponse(final Client client);
}
