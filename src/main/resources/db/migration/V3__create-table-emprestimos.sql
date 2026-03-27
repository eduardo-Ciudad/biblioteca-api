CREATE TABLE emprestimos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    livro_id BIGINT,
    usuario_id BIGINT,
    data_emprestimo DATE,
    data_devolucao DATE,

    FOREIGN KEY (livro_id) REFERENCES livros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);