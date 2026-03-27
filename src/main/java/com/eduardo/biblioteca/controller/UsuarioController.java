package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.domain.usuario.DadosCadastroUsuario;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/usuarios")
    public class UsuarioController {

        @Autowired
        private UsuarioRepository repository;

        @PostMapping
        @Transactional
        public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {

            if (repository.existsByEmail(dados.email())) {
                throw new RuntimeException("Email já cadastrado");
            }

            repository.save(new Usuario(dados));
        }
    }

