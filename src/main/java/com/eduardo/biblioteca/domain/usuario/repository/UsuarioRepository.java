package com.eduardo.biblioteca.domain.usuario.repository;

import com.eduardo.biblioteca.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    boolean existsByNome(String nome);
}
