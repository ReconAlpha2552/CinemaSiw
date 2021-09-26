package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Regista;


public interface RegistaRepository extends CrudRepository<Regista, Long> {
	
	public List<Regista> findByNome(String nome);

}