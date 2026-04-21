package com.eduardo.biblioteca.service;

import com.eduardo.biblioteca.domain.usuario.model.Usuario;
import com.eduardo.biblioteca.domain.usuario.repository.UsuarioRepository;
import com.eduardo.biblioteca.dto.input.LoginRequest;
import com.eduardo.biblioteca.security.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;



@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    public String login (LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Passa email + role para o token
        return jwtService.generateToken(usuario.getEmail(), usuario.getRole().name());
    }
}


// No seu LoginService, onde você chama generateToken,
// passe o role do usuário como segundo argumento.
//
// Antes:
//   String token = jwtService.generateToken(usuario.getEmail());
//
// Depois:
//   String token = jwtService.generateToken(usuario.getEmail(), usuario.getRole().name());
//
// usuario.getRole().name() retorna "ADMIN" ou "USER" (o nome do enum sem o prefixo ROLE_)
