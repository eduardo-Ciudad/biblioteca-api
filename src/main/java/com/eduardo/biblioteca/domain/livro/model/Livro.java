package com.eduardo.biblioteca.domain.livro.model;

import com.eduardo.biblioteca.domain.livro.dto.DadosCadastroLivro;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;



@Entity
@Table(name = "Livros")
@Setter
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
        private Boolean disponivel;

        public Boolean isDisponivel() {
            return disponivel;
        }

    public Livro(@Valid DadosCadastroLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
    }


}





