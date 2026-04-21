package com.eduardo.biblioteca.security.AuthFilter;

import com.eduardo.biblioteca.security.JwtService.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {


    //o que essa classe faz:
    // pega o token do header da requisição
    //extrai o usuario
    //valida o token
    //se tiver tudo certo valida o usuaio
    private final JwtService jwtService;// cuida do token
    private final UserDetailsService userDetailService; // procura o usuario no sql

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {



        String authHeader = request.getHeader("Authorization");

        // Se não tem token, deixa passar — o SecurityConfig vai barrar se precisar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtService.extractUsername(token);

        // Só processa se tem email e ainda não tem autenticação no contexto
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, email)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken); // esse usuario esta autenticado nessa requisição
            }
        }
        filterChain.doFilter(request, response); // continua o fluxo
    }
}
