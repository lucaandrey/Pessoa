package br.com.pessoa.pessoa.service;

import br.com.pessoa.pessoa.model.Endereco;
import br.com.pessoa.pessoa.model.Pessoa;
import br.com.pessoa.pessoa.repositories.EnderecoRepository;
import br.com.pessoa.pessoa.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final PessoaRepository pessoaRepository;

    @Transactional
    public Endereco saveAddress(Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(endereco.getPessoa().getId()).orElse(null);
        if (pessoa != null) {
            endereco.setPessoa(pessoa);
            pessoa.getEndereco().add(endereco);
            return repository.save(endereco);
        }
        return null;
    }
}
