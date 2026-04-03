package com.eduardo.biblioteca.domain.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {
    boolean existsByTituloAndAutor(String titulo, String autor);
}
