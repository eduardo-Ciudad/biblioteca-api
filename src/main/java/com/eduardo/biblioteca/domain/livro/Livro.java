package com.eduardo.biblioteca.domain.livro;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;



@Entity
@Table(name = "Livros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Livro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String autor;
        private String titulo;


    public Livro(@Valid DadosCadastroLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
    }
}



