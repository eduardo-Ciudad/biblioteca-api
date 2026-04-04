package com.eduardo.biblioteca.domain.emprestimo;

import com.eduardo.biblioteca.domain.livro.Livro;
import com.eduardo.biblioteca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Boolean ativo;
    private LocalDate dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Emprestimos (Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.ativo = true;
    }

    public Boolean isAtivo() {
        return ativo;
    }


}


