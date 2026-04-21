package com.eduardo.biblioteca.dto.input;


public record LoginRequest(String email, String senha) {
    public String getEmail() {
        return email;
    }


    public Object getSenha() {
        return senha;
    }
}

