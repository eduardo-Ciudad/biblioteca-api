package com.eduardo.biblioteca.service;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivrosRepository livroRepository;

    public LivroService(LivrosRepository livrosRepository){
        this.livroRepository = livrosRepository;
    }

    public Livro cadastrarLivro(Livro livro) {

        if (livroRepository.existsByTituloAndAutor(
                livro.getTitulo(),
                livro.getAutor())) {

            throw new RuntimeException("Livro já cadastrado");
        }

        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new RuntimeException("Título obrigatório");
        }

        return livroRepository.save(livro);
    }

    public void deletarLivro(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro está emprestado");
        }

        livroRepository.delete(livro);
    }

    private void validarLivro(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new RuntimeException("Título obrigatório");
        }
    }

    private void validarDuplicidade(Livro livro) {
        if (livroRepository.existsByTituloAndAutor(livro.getTitulo(), livro.getAutor())) {
            throw new RuntimeException("Livro já cadastrado");
        }
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }
}
