package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.emprestimo.repository.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.dto.EmprestimoRequest;
import com.eduardo.biblioteca.domain.emprestimo.model.Emprestimos;
import com.eduardo.biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository repository;

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Operation(summary = "fazer um emprestimo", description = "Realiza a criação de um emprestimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo realizado"),
            @ApiResponse(responseCode = "404", description = "Usuário ou livro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Livro já está emprestado")
    })
    @PostMapping
    public ResponseEntity<String> emprestarLivro(@RequestBody EmprestimoRequest request) {

        emprestimoService.emprestarLivro(
                request.getUsuarioId(),
                request.getLivroId()
        );

        return ResponseEntity.ok("Livro emprestado com sucesso");
    }


    @Operation(summary = "Devolver livro", description = "Realiza a devolução de um livro emprestado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro devolvido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "409", description = "Livro já foi devolvido")
    })
    @PutMapping("/{id}/devolucao")
    public ResponseEntity<String> devolverLivro(@PathVariable Long id) {
        emprestimoService.devolverLivro(id);
        return ResponseEntity.ok("Empréstimo devolvido com sucesso");
    }

    @GetMapping()
    public ResponseEntity<List<Emprestimos>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirEmprestimo(@PathVariable Long id) {
        emprestimoService.excluirEmprestimo(id);

        return ResponseEntity.noContent().build();

    }

}
