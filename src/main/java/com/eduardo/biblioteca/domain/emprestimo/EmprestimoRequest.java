package com.eduardo.biblioteca.domain.emprestimo;

import lombok.Getter;
import lombok.Setter;

public class EmprestimoRequest {
    @Setter
    @Getter

    private Long usuarioId;
    private Long livroId;


    public Long getLivroId() {
        return livroId;
    }

}
