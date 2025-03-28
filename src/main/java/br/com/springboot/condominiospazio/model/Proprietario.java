package br.com.springboot.condominiospazio.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Proprietario extends Morador {

	public Proprietario(Long codigo, String pesquisa, Tipo tipo, String nome, String documento, LocalDate dataCadastro, Apartamento apto, Contato contato, List<Veiculo> veiculos, Endereco endereco) {
		super(codigo, pesquisa, tipo, nome, documento, dataCadastro, apto, contato, veiculos);
		this.endereco = endereco;
	}

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco;

}


