package br.com.challenge.api;

import br.com.challenge.domains.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClientResponse implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -8640609717715507150L;
	
	private UUID clientIdentifier;
	
	private String firstName;
	
	private String lastName;
	
	private String document;
	
	private DocumentType documentType;
	
	private LocalDateTime creationDate;
	
	private BigDecimal balance;
	
}
