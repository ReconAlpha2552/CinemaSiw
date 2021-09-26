package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.spring.model.Produttore;
import it.uniroma3.siw.spring.repository.ProduttoreRepository;

@Service
public class ProduttoreService {
	
	@Autowired
	private ProduttoreRepository produttoreRepository; 
	
	@Transactional
	public Produttore inserisci(Produttore produttore) {
		return produttoreRepository.save(produttore);
	}

	@Transactional
	public List<Produttore> tutti() {
		return (List<Produttore>)produttoreRepository.findAll();
	}

	@Transactional
	public Produttore produttorePerId(Long id) {
		Optional<Produttore> optional = produttoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Produttore produttore) {
		List<Produttore> produttori = this.produttoreRepository.findByNome(produttore.getNome());
		if (produttori.size() > 0)
			return true; //e' possibile che due o piu esami coincidano con la stessa ora, pertanto la verifica non viene effettuata
	else 
			return false;
	}
}