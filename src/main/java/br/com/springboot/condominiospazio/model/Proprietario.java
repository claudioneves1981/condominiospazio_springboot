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
public class Proprietario extends Morador {

	public Proprietario(Long codigo, String pesquisa, Tipo tipo, String nome, String documento, LocalDate dataCadastro, Apartamento apto, Contato contato, Endereco endereco) {
		super(codigo, pesquisa, tipo, nome, documento, dataCadastro, apto, contato);
		this.endereco = endereco;
	}

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco;

}


