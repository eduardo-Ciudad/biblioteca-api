package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.livro.DadosCadastroLivro;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivrosRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroLivro dados){
        repository.save(new Livro(dados));
    }
}
