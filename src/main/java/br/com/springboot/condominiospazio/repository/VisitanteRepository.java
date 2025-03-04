package br.com.springboot.condominiospazio.repository;

import br.com.springboot.condominiospazio.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {


	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.nome)) like %?1%")
	List<Visitante> findByNome(String nome);

	List<Visitante> findByTipo(String tipo);


	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.tipo)) like %?1%")
	//List<Visitante> buscarPorTipo(String tipo);




}
