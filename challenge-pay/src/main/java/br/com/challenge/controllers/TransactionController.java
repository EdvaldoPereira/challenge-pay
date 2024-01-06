package br.com.challenge.controllers;

import br.com.challenge.api.TransactionApi;
import br.com.challenge.api.TransactionMapper;
import br.com.challenge.api.TransactionRequest;
import br.com.challenge.api.TransactionResponse;
import br.com.challenge.domains.Client;
import br.com.challenge.domains.Transaction;
import br.com.challenge.services.ClientService;
import br.com.challenge.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transaction", description = "Endpoints for execute transactions")
public class TransactionController implements TransactionApi {
	
	private final ClientService clientService;
	private final TransactionService transactionService;
	
	private final TransactionMapper transactionMapper;
	
	TransactionController(final ClientService clientService, final TransactionService transactionService,
	                      final TransactionMapper transactionMapper) {
		this.clientService = clientService;
		this.transactionService = transactionService;
		this.transactionMapper = transactionMapper;
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> createTransaction(@RequestBody final TransactionRequest request) {
		
		final Client clientReceiver = clientService.getClient(request.receiver());
		final Client clientSender = clientService.getClient(request.sender());
		
		final Transaction transaction = transactionService.createTransferTransaction(clientSender, clientReceiver,
		                                                                             request.amount());
		
		final TransactionResponse transactionResponse = transactionMapper.transactionToTransactionResponse(transaction);
		
		return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
	}
}
