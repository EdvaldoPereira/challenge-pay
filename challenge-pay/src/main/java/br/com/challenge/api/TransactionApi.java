package br.com.challenge.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionApi {
	
	@Operation(summary = "Create Transaction",
	           description = "Create Transaction",
	           tags = { "Transaction" },
	           responses = { @ApiResponse(responseCode = "200",
	                                      description = "Success",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = TransactionResponse.class))),
	                         @ApiResponse(responseCode = "404",
	                                      description = "Bad Request",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ApiErrorResponse.class))) })
	ResponseEntity<TransactionResponse> createTransaction(@RequestBody final TransactionRequest request);
}
