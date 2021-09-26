package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.spring.model.Film;
import it.uniroma3.siw.spring.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository; 
	
	@Transactional
	public Film inserisci(Film film) {
		return filmRepository.save(film);
	}

	@Transactional
	public List<Film> tutti() {
		return (List<Film>)filmRepository.findAll();
	}

	@Transactional
	public Film filmPerId(Long id) {
		Optional<Film> optional = filmRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public void elimina(Film film) {
		filmRepository.delete(film);
	}

	@Transactional
	public boolean alreadyExists(Film film) {
		List<Film> films = this.filmRepository.findByTitolo(film.getTitolo());
		if (films.size() > 0)
			return true; //e' possibile che due o piu esami coincidano con la stessa ora, pertanto la verifica non viene effettuata
	else 
			return false;
	}

	
}