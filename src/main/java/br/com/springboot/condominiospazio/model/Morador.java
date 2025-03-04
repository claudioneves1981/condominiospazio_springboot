package br.com.springboot.condominiospazio.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

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

    public Morador(Long codigo, String pesquisa, Tipo tipo, String nome, String documento, LocalDate dataCadastro, Apartamento apto, Contato contato) {
        super(codigo, pesquisa, tipo, nome, documento, dataCadastro);
        this.apto = apto;
        this.contato = contato;
    }
}
