package br.com.challenge.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface WalletApi {
	
	@Operation(summary = "Create Wallet",
	           description = "Create Wallet",
	           tags = { "Wallet" },
	           responses = { @ApiResponse(responseCode = "200",
	                                      description = "Success",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = WalletResponse.class))),
	                         @ApiResponse(responseCode = "404",
	                                      description = "Bad Request",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ApiErrorResponse.class))) })
	ResponseEntity<WalletResponse> createWallet(@RequestBody final WalletRequest request);
}
