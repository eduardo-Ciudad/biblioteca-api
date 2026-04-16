package com.eduardo.biblioteca.dto.input;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLivro(
        @NotBlank(message = "não pode estar vazio")
        String titulo,

        @NotBlank(message = "não pode estar vazio")
        String autor,

        Boolean disponivel
) {}
