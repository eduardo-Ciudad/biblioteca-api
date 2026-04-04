package com.eduardo.biblioteca.domain.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimos, Long> {
    long countByUsuarioId(Long usuarioID);
}
