package br.com.challenge.api;

import br.com.challenge.domains.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(@NotBlank @NotNull String firstName,
                            @NotBlank @NotNull String lastName,
                            @NotBlank @NotNull String document,
                            DocumentType documentType,
                            @NotBlank @NotNull String email,
                            @NotBlank @NotNull String password) {
}