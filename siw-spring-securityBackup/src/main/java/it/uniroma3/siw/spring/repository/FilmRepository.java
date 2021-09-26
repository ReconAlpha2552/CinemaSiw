package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Film;


public interface FilmRepository extends CrudRepository<Film, Long> {
	
	public List<Film> findByTitolo(String titolo);

}