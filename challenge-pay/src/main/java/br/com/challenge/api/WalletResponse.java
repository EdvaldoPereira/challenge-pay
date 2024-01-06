package br.com.challenge.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class WalletResponse implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -1867304279250511812L;
	
	private LocalDateTime creationDate;
	private UUID clientIdentifier;
	private BigDecimal balance;
}
