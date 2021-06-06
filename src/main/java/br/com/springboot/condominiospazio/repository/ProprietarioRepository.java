package br.com.springboot.condominiospazio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.springboot.condominiospazio.model.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

	@Query(value = "select p from Proprietario p where upper(trim(p.nome)) like %?1%")
	List<Proprietario> buscarPorNome(String name);
}
