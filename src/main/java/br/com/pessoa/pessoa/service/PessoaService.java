package br.com.pessoa.pessoa.service;

import br.com.pessoa.pessoa.model.Endereco;
import br.com.pessoa.pessoa.model.Pessoa;
import br.com.pessoa.pessoa.repositories.EnderecoRepository;
import br.com.pessoa.pessoa.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }


    public List<Pessoa> findAll() {

        List<Pessoa> pessoas = repository.findAll();
        pessoas.forEach(p -> Hibernate.initialize(p.getEndereco()));
        return pessoas;
    }


    @Transactional
    public Pessoa update(Pessoa pessoa) {
        if (repository.existsById(pessoa.getId())){
          return  repository.save(pessoa);
        }
        throw new EntityNotFoundException("Pessoa não encontrada");
    }


    public List<Endereco> listEnderecos(Long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        return pessoa.getEndereco();
    }

    public List<Pessoa> findByName(String nome) {
        return repository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Endereco findmainAddress(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com id " + id + " não encontrada"));
        return pessoa.getEndereco().stream()
                .filter(Endereco::isPrincipal)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Endereço principal da pessoa com id " + id + " não encontrado"));
    }
}
