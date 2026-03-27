package com.eduardo.biblioteca.domain.livro;

public record DadosCadastroLivro(
        String titulo,
        String autor,
        Boolean disponivel
) {
}
