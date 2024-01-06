package br.com.challenge.controllers;

import br.com.challenge.api.WalletApi;
import br.com.challenge.api.WalletMapper;
import br.com.challenge.api.WalletRequest;
import br.com.challenge.api.WalletResponse;
import br.com.challenge.domains.Client;
import br.com.challenge.domains.Wallet;
import br.com.challenge.services.ClientService;
import br.com.challenge.services.WalletService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/wallets")
@Tag(name = "Wallet", description = "Endpoints for create Wallets")
public class WalletController implements WalletApi {
	
	private final WalletMapper walletMapper;
	private final WalletService walletService;
	private final ClientService clientService;
	
	WalletController(final WalletMapper walletMapper, final WalletService walletService,
	                 final ClientService clientService) {
		this.walletMapper = walletMapper;
		this.walletService = walletService;
		this.clientService = clientService;
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletResponse> createWallet(@RequestBody final WalletRequest request) {
		
		final Client client = clientService.getClient(request.clientIdentifier());
		final Wallet wallet = new Wallet(request.balance(), client);
		walletService.createWallet(wallet, request.balance());
		final WalletResponse walletResponse = walletMapper.walletToWalletResponse(wallet);
		
		return new ResponseEntity<>(walletResponse, HttpStatus.CREATED);
	}
}
