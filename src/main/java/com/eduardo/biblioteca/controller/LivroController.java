package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.domain.livro.DadosCadastroLivro;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivrosRepository repository;
    private EmprestimoService emprestimoService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroLivro dados){
        repository.save(new Livro(dados));
    }


}
