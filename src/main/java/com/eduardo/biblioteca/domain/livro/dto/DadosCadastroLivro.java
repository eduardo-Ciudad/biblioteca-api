package com.eduardo.biblioteca.domain.livro.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLivro(
        @NotBlank(message = "não pode estar vazio")
        String titulo,

        @NotBlank(message = "não pode estar vazio")
        String autor,

        Boolean disponivel
) {}
