package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.dto.input.DadosCadastroLivro;
import com.eduardo.biblioteca.domain.livro.model.Livro;
import com.eduardo.biblioteca.domain.livro.repository.LivrosRepository;
import com.eduardo.biblioteca.dto.output.DadosRespostaLivro;
import com.eduardo.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Operation(summary = "Criar um novo livro", description = "Cadastra um novo livro no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRespostaLivro> cadastrarLivro(@RequestBody @Valid DadosCadastroLivro dados){
       Livro livroSalvo =  livroService.cadastrarLivro(dados);
        return ResponseEntity.status(201).body(new DadosRespostaLivro(livroSalvo));
    }


    @Operation(summary = "Deletar um livro", description = "Remove um livro do sistema pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso")
    })


    @GetMapping()
    public ResponseEntity<List<DadosRespostaLivro>> listarLivros() {
        return ResponseEntity.ok(livroService.listarTodos().stream().map(DadosRespostaLivro::new).toList());
    }


    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public DadosRespostaLivro buscarPorId(@PathVariable Long id) {
        return new DadosRespostaLivro(livroService.buscarPorId(id));
    }

}
