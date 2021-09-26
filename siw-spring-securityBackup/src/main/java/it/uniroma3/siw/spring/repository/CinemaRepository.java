package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Cinema;


public interface CinemaRepository extends CrudRepository<Cinema, Long> {
	
	public List<Cinema> findByNome(String nome);

}