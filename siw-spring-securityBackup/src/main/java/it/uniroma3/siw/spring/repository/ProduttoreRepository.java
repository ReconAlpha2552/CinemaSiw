package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Produttore;


public interface ProduttoreRepository extends CrudRepository<Produttore, Long> {
	
	public List<Produttore> findByNome(String nome);

}