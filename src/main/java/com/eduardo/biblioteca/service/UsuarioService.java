package com.eduardo.biblioteca.service;


import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import com.eduardo.biblioteca.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarLivro(Usuario usuario) {

        if (usuarioRepository.existsByNome(
                usuario.getNome())) {

            throw new RuntimeException("Livro já cadastrado");
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));


        usuarioRepository.delete(usuario);
    }


    private void validarDuplicidade(Usuario usuario) {
        if (usuarioRepository.existsByNome(usuario.getNome())) {
            throw new RuntimeException("Usuario já cadastrado");
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado"));
        return usuario;
    }
}
