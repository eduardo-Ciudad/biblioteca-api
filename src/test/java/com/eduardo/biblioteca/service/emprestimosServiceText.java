package com.eduardo.biblioteca.service;

import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.livro.LivrosRepository;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import com.eduardo.biblioteca.domain.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmprestimosServiceText {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivrosRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;


    @Test
    void deveCriarEmprestimoComSucesso() {

        Long usuarioId = 1L;
        Long livroId = 1L;

        Usuario usuario = new Usuario();
        Livro livro = new Livro();
        livro.setDisponivel(true);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livro));

        //Act
        emprestimoService.emprestarLivro(usuarioId, livroId);

        // Assert
        verify(emprestimoRepository).save(any(Emprestimos.class));
    }

    @Test
    void deveLancarErroQuandoUsuarioNaoExiste() {
        Long usuarioId = 1L;
        Long livroId = 1L;

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            emprestimoService.emprestarLivro(usuarioId, livroId);
        });
    }

    void deveLancarErroQuandoLivroNaoDisponivel() {
        Long usuarioId = 1L;
        Long livroId = 1L;

        Usuario usuario = new Usuario();
        Livro livro = new Livro();
        livro.setDisponivel(false);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livro));

        assertThrows(RuntimeException.class, () -> {
            emprestimoService.emprestarLivro(usuarioId, livroId);
        });
    }

    @Test
    void deveLancarErroSeLivroJaDevolvido() {
        Emprestimos emp = new Emprestimos();
        emp.setDataDevolucao(LocalDateTime.now());

        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emp));

        assertThrows(RuntimeException.class, () -> {
            emprestimoService.devolverLivro(1L);
        });
    }
}
