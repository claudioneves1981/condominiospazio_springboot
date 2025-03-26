package br.com.springboot.condominiospazio.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Morador extends Pessoa {

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ID_APTO")
    private Apartamento apto;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ID_CONTATO")
    private Contato contato;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="TB_VEICULO_PESSOA",
            joinColumns = @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA"),
            inverseJoinColumns = @JoinColumn(name = "ID_VEICULO",referencedColumnName = "ID_VEICULO")
    )
    private List<Veiculo> veiculos;

    public Morador(Long codigo, String pesquisa, Tipo tipo, String nome, String documento, LocalDate dataCadastro, Apartamento apto, Contato contato, List<Veiculo> veiculos) {
        super(codigo, pesquisa, tipo, nome, documento, dataCadastro);
        this.apto = apto;
        this.contato = contato;
        this.veiculos = veiculos;
    }
}
