package com.eduardo.biblioteca.service;


import com.eduardo.biblioteca.domain.emprestimo.EmprestimoRepository;
import com.eduardo.biblioteca.domain.emprestimo.Emprestimos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    public Emprestimos criarEmprestimos(Emprestimos emprestimo){
        if (emprestimo.getLivro() == null){
            throw new RuntimeException("Livro é obrigatorio");
        }
        return emprestimoRepository.save(emprestimo);
    }

}
