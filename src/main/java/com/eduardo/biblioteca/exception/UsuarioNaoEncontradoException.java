package com.eduardo.biblioteca.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super (mensagem);
    }
}
