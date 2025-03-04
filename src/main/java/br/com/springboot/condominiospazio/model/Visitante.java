package br.com.springboot.condominiospazio.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visitante extends Pessoa {

	private String observacao;

}