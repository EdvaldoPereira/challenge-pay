package br.com.challenge.api;

import br.com.challenge.domains.TransactionState;
import br.com.challenge.domains.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionResponse implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -6707551087651105725L;
	
	private LocalDateTime creationDate;
	private BigDecimal amount;
	private UUID sender;
	private UUID receiver;
	private TransactionType transactionType;
	private TransactionState transactionState;
	private String messageResponse;
}
