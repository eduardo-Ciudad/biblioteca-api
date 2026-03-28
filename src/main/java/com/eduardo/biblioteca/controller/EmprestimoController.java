package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService service;

    private final EmprestimoRepository repository;

    public EmprestimoController(EmprestimoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Emprestimos criar(@RequestBody Emprestimos emprestimo) {

        return service.criarEmprestimos(emprestimo);
    }

    @GetMapping
    public List<Emprestimos> listar() {

        return repository.findAll();
    }
}