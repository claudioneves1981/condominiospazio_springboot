package br.com.springboot.condominiospazio.repository;

import br.com.springboot.condominiospazio.model.Morador;
import br.com.springboot.condominiospazio.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

	@Query(value = "SELECT obj FROM Morador obj WHERE LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%')) and LOWER(obj.tipo) LIKE LOWER(CONCAT('%','MORADOR','%'))")
	List<Morador> findByNome(@Param("nome") String nome);

	List<Morador> findByDocumento(String cpf);


}
