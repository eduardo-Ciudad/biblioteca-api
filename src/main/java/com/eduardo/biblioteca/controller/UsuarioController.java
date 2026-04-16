package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.usuario.model.Usuario;
import com.eduardo.biblioteca.dto.input.DadosCadastroUsuario;
import com.eduardo.biblioteca.dto.output.DadosRespostaUsuario;
import com.eduardo.biblioteca.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Criar usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRespostaUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados){
       Usuario usuarioSalvo =  usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.status(201).body(new DadosRespostaUsuario(usuarioSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<DadosRespostaUsuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos().stream().map(DadosRespostaUsuario::new).toList());
    }


    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public DadosRespostaUsuario buscarPorId(@PathVariable Long id) {
        return new DadosRespostaUsuario(usuarioService.buscarPorId(id));
    }
    }

