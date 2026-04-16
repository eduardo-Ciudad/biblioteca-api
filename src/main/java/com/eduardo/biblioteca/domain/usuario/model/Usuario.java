package com.eduardo.biblioteca.domain.usuario.model;

import com.eduardo.biblioteca.dto.input.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;


    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
    }
}

