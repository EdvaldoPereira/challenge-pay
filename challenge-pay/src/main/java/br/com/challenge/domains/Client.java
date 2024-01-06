package br.com.challenge.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Setter
@Entity
@Table(name = "clients", schema = "public")
public class Client implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 2341697437943261796L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "document")
	private String document;
	
	@Column(name = "document_type")
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "client_identifier", columnDefinition = "uuid")
	private UUID clientIdentifier;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
	private Wallet wallet;
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private static List<Transaction> sentTransactions = new ArrayList<>();
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private static List<Transaction> receivedTransactions = new ArrayList<>();
	
	public Client() {
		this.creationDate = LocalDateTime.now();
		this.clientIdentifier = UUID.randomUUID();
	}
	
	public BigDecimal getBalance() {
		
		if (isNull(this.wallet)) {
			return BigDecimal.ZERO;
		}
		
		return this.wallet.getBalance();
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Client client = (Client) o;
		return Objects.equals(id, client.id) && Objects.equals(document, client.document);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, document);
	}
	
	public void cashOut(final BigDecimal amount) {
		this.wallet.cashOut(amount);
	}
	
	public void cashIn(final BigDecimal amount) {
		this.wallet.cashIn(amount);
	}
}
