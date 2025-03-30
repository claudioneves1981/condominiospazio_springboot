package br.com.springboot.condominiospazio.repository;

import br.com.springboot.condominiospazio.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

   Veiculo findByPlaca(String placa);

   List<Veiculo> findByMorador(String morador);

}
