package com.eduardo.biblioteca.service;


import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import com.eduardo.biblioteca.exception.RecursoNaoEncontradoException;
import com.eduardo.biblioteca.exception.RegrasDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivrosRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             UsuarioRepository usuarioRepository,
                             LivrosRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional
    public void emprestarLivro(Long usuarioID, Long livroID){


        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));




        Livro livro = livroRepository.findById(livroID)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado"));

        if (!livro.isDisponivel()) {
            throw new RegrasDeNegocioException("Livro não está disponível");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        long quantidadeEmprestimos = emprestimoRepository.countByUsuarioId(usuarioID);

        if(quantidadeEmprestimos >= 5) {
            throw new RegrasDeNegocioException("Usuário atingiu o limite de livros");
        }

        Emprestimos emprestimo = new Emprestimos(livro, usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void devolverLivro(Long emprestimoID) {

        Emprestimos emprestimo = emprestimoRepository.findById(emprestimoID)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado"));

        if (!emprestimo.isAtivo()) {
            throw new RegrasDeNegocioException("Esse empréstimo já foi finalizado");
        }

        Livro livro = emprestimo.getLivro();

        if (livro.isDisponivel()) {
            throw new RegrasDeNegocioException("Livro já está disponível");
        }

        livro.setDisponivel(true);

        emprestimo.setAtivo(false);
        emprestimo.setDataDevolucao(LocalDateTime.now());
    }


    public List<Emprestimos> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public void excluirEmprestimo(Long emprestimoID){
        Emprestimos emprestimos = emprestimoRepository.findById(emprestimoID)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));

        emprestimoRepository.delete(emprestimos);
    }
}
