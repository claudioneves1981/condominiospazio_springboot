package br.com.springboot.condominiospazio.repository;

import br.com.springboot.condominiospazio.model.Morador;
import br.com.springboot.condominiospazio.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.nome)) like %?1%")
	List<Morador> findByNome(String nome);


	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.tipo)) like %?1%")
	//List<Visitante> buscarPorTipo(String tipo);




}
