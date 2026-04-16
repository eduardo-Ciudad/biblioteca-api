package com.eduardo.biblioteca.dto.output;

import com.eduardo.biblioteca.domain.usuario.model.Usuario;

public record DadosRespostaUsuario(
        Long id,
        String nome,
        String email
) {
    public DadosRespostaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
