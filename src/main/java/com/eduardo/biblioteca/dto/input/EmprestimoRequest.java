package com.eduardo.biblioteca.dto.input;

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
