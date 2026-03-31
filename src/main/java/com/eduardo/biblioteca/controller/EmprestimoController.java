package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRequest;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<String> emprestarLivro(@RequestBody EmprestimoRequest request) {

        emprestimoService.emprestarLivro(
                request.getUsuarioId(),
                request.getLivroId()
        );

        return ResponseEntity.ok("Livro emprestado com sucesso");
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<String> devolverLivro(@PathVariable Long id) {

        emprestimoService.devolverLivro(id);

        return ResponseEntity.ok("Livro devolvido com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Emprestimos>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }
}
