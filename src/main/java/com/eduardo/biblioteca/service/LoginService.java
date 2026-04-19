package com.eduardo.biblioteca.service;

import com.eduardo.biblioteca.domain.usuario.model.Usuario;
import com.eduardo.biblioteca.domain.usuario.repository.UsuarioRepository;
import com.eduardo.biblioteca.dto.input.LoginRequest;
import com.eduardo.biblioteca.security.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    public String login (LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(request.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(usuario.getEmail());
    }
}
