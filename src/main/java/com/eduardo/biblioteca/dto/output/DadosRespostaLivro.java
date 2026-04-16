package com.eduardo.biblioteca.dto.output;

import com.eduardo.biblioteca.domain.livro.model.Livro;

public record DadosRespostaLivro(
        Long id,
        String titulo,
        String autor,
        Boolean disponivel
) {
    public DadosRespostaLivro(Livro livro){
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getDisponivel());
    }
}
