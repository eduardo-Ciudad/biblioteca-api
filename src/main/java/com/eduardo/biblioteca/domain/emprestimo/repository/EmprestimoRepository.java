package com.eduardo.biblioteca.domain.emprestimo.repository;

import com.eduardo.biblioteca.domain.emprestimo.model.Emprestimos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimos, Long> {
    long countByUsuarioId(Long usuarioID);
}
