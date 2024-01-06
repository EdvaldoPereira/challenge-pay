package br.com.challenge.api;

import br.com.challenge.domains.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {
	
	@Mapping(target = "clientIdentifier", source = "client.clientIdentifier")
	WalletResponse walletToWalletResponse(Wallet wallet);
	
}
