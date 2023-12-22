package br.com.challenge.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 2341697437943261796L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
	private Wallet wallet;
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private List<Transaction> sentTransactions;
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private List<Transaction> receivedTransactions;
	
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
}
