package br.com.challenge.domains;

import br.com.challenge.exceptions.InsufficientFundsException;
import br.com.challenge.exceptions.InvalidAmountException;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "wallets", schema = "public")
public class Wallet implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -3914517628767868084L;
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Getter
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Getter
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Getter
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Wallet() {
	}
	
	public Wallet(final BigDecimal balance, final Client client) {
		this.creationDate = LocalDateTime.now();
		this.balance = balance;
		this.client = client;
	}
	
	public void cashIn(final BigDecimal amount) {
		
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidAmountException("Invalid Cash In " + amount);
		}
		
		this.balance = new BigDecimal(String.valueOf(this.balance.add(amount)));
	}
	
	public void cashOut(final BigDecimal amount) {
		
		if (this.balance.compareTo(amount) < 0) {
			throw new InsufficientFundsException("Insufficient Funds " + this.balance);
		}
		
		this.balance = new BigDecimal(String.valueOf(this.balance.subtract(amount)));
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Wallet wallet = (Wallet) o;
		return Objects.equals(id, wallet.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
