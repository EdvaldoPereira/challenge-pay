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
@Table(name = "transactions")
public class Transaction implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -5517553710438647119L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "sender")
	private Client sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver")
	private Client receiver;
	
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
}
