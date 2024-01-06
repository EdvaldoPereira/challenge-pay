package br.com.challenge.services;

import br.com.challenge.api.AuthorizationResponse;
import br.com.challenge.api.NotifierClient;
import br.com.challenge.domains.Client;
import br.com.challenge.domains.Transaction;
import br.com.challenge.domains.TransactionType;
import br.com.challenge.exceptions.TransactionNotSupported;
import br.com.challenge.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final AuthorizerService authorizerService;
	
	private final NotifierClient notifierClient;
	
	public TransactionService(final TransactionRepository transactionRepository,
	                          final AuthorizerService authorizerService, final NotifierClient notifierClient) {
		this.transactionRepository = transactionRepository;
		this.authorizerService = authorizerService;
		this.notifierClient = notifierClient;
	}
	
	public void createDepositTransaction(final Client client, final BigDecimal amount) {
		
		final Transaction transaction = new Transaction();
		transaction.setTransactionType(TransactionType.DEPOSITO);
		transaction.setReceiver(client);
		transaction.setAmount(amount);
		
		transactionRepository.save(transaction);
	}
	
	public Transaction createTransferTransaction(final Client clientSender, final Client clientReceiver,
	                                             final BigDecimal amount) {
		
		final Transaction transaction = new Transaction(clientSender, clientReceiver, amount);
		transaction.setTransactionType(TransactionType.TRANSFERENCIA);
		final boolean transferRulesSuccess = executeTransferRules(clientSender, clientReceiver);
		
		if (transferRulesSuccess) {
			transaction.transfer();
			transaction.approved();
		} else {
			transaction.denied(null);
		}
		
		transactionRepository.save(transaction);
		notifierClient.notifier();
		
		return transaction;
	}
	
	private boolean executeTransferRules(final Client clientSender, final Client clientReceiver) {
		
		if (isNotValidTransaction(clientSender, clientReceiver)) {
			throw new TransactionNotSupported("Transaction not supported");
		}
		
		final AuthorizationResponse authorize = authorizerService.authorize();
		
		return !authorize.isNotApproved();
	}
	
	private boolean isNotValidTransaction(final Client clientSender, final Client clientReceiver) {
		return clientSender.getDocumentType().isMerchant() && clientReceiver.getDocumentType().isCommon();
	}
}
