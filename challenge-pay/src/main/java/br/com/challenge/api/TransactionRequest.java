package br.com.challenge.api;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(UUID sender, UUID receiver, BigDecimal amount) {
}
