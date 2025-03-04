package br.com.springboot.condominiospazio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seq_pessoa" , sequenceName = "seq_pessoa", allocationSize = 1)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long codigo;
    private String pesquisa;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String nome;
    private String documento;

    private LocalDate dataCadastro;


}
