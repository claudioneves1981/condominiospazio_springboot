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
@SequenceGenerator(name = "seq_contato" , sequenceName = "seq_contato", allocationSize = 1)
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contato")
    @Column(name = "ID_CONTATO")
    private Long id;

    private String telefone;

    private String email;

}
