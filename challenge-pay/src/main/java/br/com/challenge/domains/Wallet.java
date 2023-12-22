package br.com.challenge.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -3914517628767868084L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
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
