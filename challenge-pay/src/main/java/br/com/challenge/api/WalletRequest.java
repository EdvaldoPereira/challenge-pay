package br.com.challenge.api;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletRequest(UUID clientIdentifier, BigDecimal balance) {
}