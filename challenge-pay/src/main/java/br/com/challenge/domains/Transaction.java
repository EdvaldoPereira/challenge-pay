package br.com.challenge.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "transactions", schema = "public")
public class Transaction implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -5517553710438647119L;
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Getter
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Getter
	@Setter
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_state")
	private TransactionState transactionState;
	
	@Getter
	@Setter
	@Column(name = "message_response")
	private String messageResponse;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "sender")
	private Client sender;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "receiver")
	private Client receiver;
	
	public Transaction() {
		this.creationDate = LocalDateTime.now();
	}
	
	public Transaction(final Client sender, final Client receiver, final BigDecimal amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.creationDate = LocalDateTime.now();
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Transaction that = (Transaction) o;
		return Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public void transfer() {
		this.sender.cashOut(amount);
		this.receiver.cashIn(amount);
	}
	
	public void reverse() {
		this.receiver.cashOut(amount);
		this.sender.cashIn(amount);
	}
	
	public void approved() {
		
		this.transactionState = TransactionState.APPROVED;
		this.messageResponse = TransactionState.APPROVED.name();
	}
	
	public void denied(final String message) {
		
		this.transactionState = TransactionState.DENIED;
		this.messageResponse = nonNull(message) ? message : TransactionState.APPROVED.name();
	}
}
