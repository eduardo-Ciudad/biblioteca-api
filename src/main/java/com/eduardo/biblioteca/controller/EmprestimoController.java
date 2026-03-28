package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository repository;

    public EmprestimoController(EmprestimoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Emprestimos criar(@RequestBody Emprestimos emprestimo) {
        return repository.save(emprestimo);
    }

    @GetMapping
    public List<Emprestimos> listar() {
        return repository.findAll();
    }
}