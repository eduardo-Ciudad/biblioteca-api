package com.eduardo.biblioteca.domain.livro.repository;

import com.eduardo.biblioteca.domain.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {
    boolean existsByTituloAndAutor(String titulo, String autor);
}
