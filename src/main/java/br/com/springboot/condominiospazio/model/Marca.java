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
@SequenceGenerator(name = "seq_marca" , sequenceName = "seq_marca", allocationSize = 1)
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca")
    @Column(name = "ID_MARCA")
    private Long id;

    private String code;

    private String name;

}
