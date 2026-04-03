package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.domain.usuario.DadosCadastroUsuario;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import com.eduardo.biblioteca.service.LivroService;
import com.eduardo.biblioteca.service.UsuarioService;
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


    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid Usuario usuario){
       Usuario usuarioSalvo =  usuarioService.cadastrarLivro(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    }

