package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Cinema;
import it.uniroma3.siw.spring.repository.CinemaRepository;

@Service
public class CinemaService {
	
	@Autowired
	private CinemaRepository cinemaRepository; 
	
	@Transactional
	public Cinema inserisci(Cinema cinema) {
		return cinemaRepository.save(cinema);
	}
	
	@Transactional
	public void elimina(Cinema cinema) {
		cinemaRepository.delete(cinema);
	}
	

	@Transactional
	public List<Cinema> tutti() {
		return (List<Cinema>)cinemaRepository.findAll();
	}

	@Transactional
	public Cinema cinemaPerId(Long id) {
		Optional<Cinema> optional = cinemaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Cinema cinemaPerNome(String nome) {
		List<Cinema> lista = cinemaRepository.findByNome(nome);
		if (!lista.isEmpty())
			return lista.get(0);
		else 
			return null;
	}


	@Transactional
	public boolean alreadyExists(Cinema cinema) {
		List<Cinema> cinemi = this.cinemaRepository.findByNome(cinema.getNome());
		if (cinemi.size() > 0)
			return true; //e' possibile che due o piu esami coincidano con la stessa ora, pertanto la verifica non viene effettuata
	else 
			return false;
	}
}