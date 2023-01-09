package br.com.pessoa.pessoa.controllers;

import br.com.pessoa.pessoa.model.Endereco;
import br.com.pessoa.pessoa.model.Pessoa;
import br.com.pessoa.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    public ResponseEntity save(@RequestBody Pessoa pessoa) {
        service.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }


    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pessoa>> findByName(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.findByName(nome));
    }

    @GetMapping("/{id}/lista-enderecos")
    public ResponseEntity<List<Endereco>> listEndereco(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.listEnderecos(id));
    }

    @GetMapping("/{id}/endereco-principal")
    public ResponseEntity mainAddress(@PathVariable Long id){
        var mainAddress = service.findmainAddress(id);
         return ResponseEntity.ok(mainAddress);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = service.update(pessoa);
        if (updatedPessoa != null) {
            return ResponseEntity.ok(updatedPessoa);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}


