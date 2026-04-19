package com.eduardo.biblioteca.service;


import com.eduardo.biblioteca.domain.usuario.model.Usuario;
import com.eduardo.biblioteca.domain.usuario.repository.UsuarioRepository;
import com.eduardo.biblioteca.dto.input.DadosCadastroUsuario;
import com.eduardo.biblioteca.dto.input.LoginRequest;
import com.eduardo.biblioteca.exception.RegrasDeNegocioException;
import com.eduardo.biblioteca.exception.UsuarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public Usuario cadastrarUsuario(DadosCadastroUsuario dados) {

        if (usuarioRepository.existsByNome(dados.nome())) {
            throw new RegrasDeNegocioException("Usuario já cadastrado");
        }

        if (dados.nome() == null || dados.nome().isBlank()) {
            throw new RegrasDeNegocioException("Nome é obrigatório");
        }

        Usuario usuario = new Usuario(dados); // ou builder/setters

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));


        usuarioRepository.delete(usuario);
    }


    private void validarDuplicidade(Usuario usuario) {
        if (usuarioRepository.existsByNome(usuario.getNome())) {
            throw new RegrasDeNegocioException("Usuario já cadastrado");
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
        return usuario;
    }



}
