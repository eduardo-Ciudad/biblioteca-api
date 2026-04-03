package com.eduardo.biblioteca.service;


import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(usuario.getQuantidadeLivros() >= 5) {
            throw new RuntimeException("Usuário atingiu o limite de livros");
        }

        Livro livro = livroRepository.findById(livroID)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro não está disponível");
        }


        livro.setDisponivel(false);
        livroRepository.save(livro);

        Emprestimos emprestimo = new Emprestimos(livro, usuario);
        emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void devolverLivro(Long emprestimoID) {

        Emprestimos emprestimo = emprestimoRepository.findById(emprestimoID)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (!emprestimo.isAtivo()) {
            throw new RuntimeException("Esse empréstimo já foi finalizado");
        }

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);

        emprestimo.setAtivo(false);
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
