package br.com.challenge.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ClientApi {
	
	@Operation(summary = "Create Client",
	           description = "Create Client",
	           tags = { "Client" },
	           responses = { @ApiResponse(responseCode = "200",
	                                      description = "Success",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ClientResponse.class))),
	                         @ApiResponse(responseCode = "404",
	                                      description = "Bad Request",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ApiErrorResponse.class))) })
	ResponseEntity<ClientResponse> createClient(@Validated @RequestBody final ClientRequest request);
	
	@Operation(summary = "Find Client",
	           description = "Find Client",
	           tags = { "Client" },
	           responses = { @ApiResponse(responseCode = "200",
	                                      description = "Success",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ClientResponse.class))),
	                         @ApiResponse(responseCode = "404",
	                                      description = "Bad Request",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ApiErrorResponse.class))) })
	ResponseEntity<ClientResponse> getClient(@PathVariable final UUID clientIdentifier);
	
	@Operation(summary = "Return All Clients",
	           description = "Return All Clients",
	           tags = { "Client" },
	           responses = { @ApiResponse(responseCode = "200",
	                                      description = "Success",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ClientResponse.class))),
	                         @ApiResponse(responseCode = "404",
	                                      description = "Bad Request",
	                                      content = @Content(mediaType = "application/json",
	                                                         schema = @Schema(implementation = ApiErrorResponse.class))) })
	ResponseEntity<List<ClientResponse>> getClients();
}
