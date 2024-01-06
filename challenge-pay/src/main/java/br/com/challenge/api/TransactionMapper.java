package br.com.challenge.api;

import br.com.challenge.domains.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
	
	@Mapping(target = "sender", source = "sender.clientIdentifier")
	@Mapping(target = "receiver", source = "receiver.clientIdentifier")
	TransactionResponse transactionToTransactionResponse(Transaction transaction);
}
