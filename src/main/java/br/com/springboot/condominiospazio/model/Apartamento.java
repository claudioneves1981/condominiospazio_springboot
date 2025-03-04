package br.com.springboot.condominiospazio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seq_apto" , sequenceName = "seq_apto", allocationSize = 1)
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_apto")
    @Column(name = "ID_APTO")
    private Long id;

    private String apto;

    private String bloco;

}
