package br.com.pessoa.pessoa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private Boolean principalEndereco;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("endereco")
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;


    public boolean isPrincipal() {
        return principalEndereco;
    }

}
