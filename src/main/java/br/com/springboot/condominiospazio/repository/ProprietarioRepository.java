package br.com.springboot.condominiospazio.repository;

import java.util.List;

import br.com.springboot.condominiospazio.model.Pessoa;
import br.com.springboot.condominiospazio.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.nome)) like %?1%")
	List<Proprietario> findByNome(String nome);


	//@Query(nativeQuery = true, value = "select p from pessoa p where upper(trim(p.tipo)) like %?1%")
	//List<Visitante> buscarPorTipo(String tipo);




}
