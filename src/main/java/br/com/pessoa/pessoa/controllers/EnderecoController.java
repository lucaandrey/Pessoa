package br.com.pessoa.pessoa.controllers;

import br.com.pessoa.pessoa.model.Endereco;
import br.com.pessoa.pessoa.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salvar-endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;


    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        Endereco saveEndereco = enderecoService.saveAddress(endereco);
        if (saveEndereco != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEndereco);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
