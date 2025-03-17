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
@SequenceGenerator(name = "seq_modelo" , sequenceName = "seq_modelo", allocationSize = 1)
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modelo")
    @Column(name = "ID_MODELO")
    private Long id;

    private String code;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name="TB_MARCA_MODELO",joinColumns =
    @JoinColumn( name = "ID_MODELO",referencedColumnName="ID_MODELO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MARCA",referencedColumnName="ID_MARCA"))
    private Marca marca;


}
