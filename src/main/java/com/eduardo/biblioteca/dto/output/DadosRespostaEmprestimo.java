package com.eduardo.biblioteca.dto.output;

import com.eduardo.biblioteca.domain.emprestimo.model.Emprestimos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosRespostaEmprestimo(

    Long id,
    Long livroId,
    String tituloLivro,
    Long usuarioId,
    String nomeUsuario,
    LocalDate dataEmprestimo,
    LocalDateTime dataDevolucao,
    Boolean ativo

) {
    public DadosRespostaEmprestimo(Emprestimos emp){
        this(   emp.getId(),
                emp.getLivro().getId(),
                emp.getLivro().getTitulo(),
                emp.getUsuario().getId(),
                emp.getUsuario().getNome(),
                emp.getDataEmprestimo(),
                emp.getDataDevolucao(),
                emp.getAtivo()
        );
    }
}
