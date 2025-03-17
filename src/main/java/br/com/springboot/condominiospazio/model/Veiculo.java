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
@SequenceGenerator(name = "seq_veiculo" , sequenceName = "seq_veiculo", allocationSize = 1)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    @Column(name = "ID_VEICULO")
    private Long id;

    private String placa;

    private String cor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name="TB_MARCA_VEICULO",joinColumns =
    @JoinColumn( name = "ID_VEICULO",referencedColumnName="ID_VEICULO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MARCA",referencedColumnName="ID_MARCA"))
    private Marca marca;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name="TB_MODELO_VEICULO",joinColumns =
    @JoinColumn( name = "ID_VEICULO",referencedColumnName="ID_VEICULO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MODELO",referencedColumnName="ID_MODELO"))

    private Modelo modelo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name="TB_MORADOR_VEICULO",joinColumns =
    @JoinColumn( name = "ID_VEICULO",referencedColumnName="ID_VEICULO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PESSOA",referencedColumnName="ID_PESSOA"))

    private Morador morador;

}
