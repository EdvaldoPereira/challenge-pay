package br.com.challenge.services;

import br.com.challenge.domains.Wallet;
import br.com.challenge.exceptions.ResourceDataIntegrityViolationException;
import br.com.challenge.repositories.WalletRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
	
	private final WalletRepository walletRepository;
	private final TransactionService transactionService;
	
	public WalletService(final WalletRepository walletRepository, final TransactionService transactionService) {
		this.walletRepository = walletRepository;
		this.transactionService = transactionService;
	}
	
	public void createWallet(final Wallet wallet, final BigDecimal balance) {
		
		try {
			walletRepository.save(wallet);
			transactionService.createDepositTransaction(wallet.getClient(), balance);
		} catch (DataIntegrityViolationException e) {
			throw new ResourceDataIntegrityViolationException(
					"The customer " + wallet.getClient().getFirstName() + " already has a wallet");
		}
	}
}
