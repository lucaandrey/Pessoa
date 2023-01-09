package br.com.pessoa.pessoa.repositories;


import br.com.pessoa.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);
}
