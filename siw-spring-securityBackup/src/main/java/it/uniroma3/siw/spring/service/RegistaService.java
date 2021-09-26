package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.spring.model.Regista;
import it.uniroma3.siw.spring.repository.RegistaRepository;

@Service
public class RegistaService {
	
	@Autowired
	private RegistaRepository registaRepository; 
	
	@Transactional
	public Regista inserisci(Regista regista) {
		return registaRepository.save(regista);
	}

	@Transactional
	public List<Regista> tutti() {
		return (List<Regista>)registaRepository.findAll();
	}

	@Transactional
	public Regista produttorePerId(Long id) {
		Optional<Regista> optional = registaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Regista regista) {
		List<Regista> registi = this.registaRepository.findByNome(regista.getNome());
		if (registi.size() > 0)
			return true; //e' possibile che due o piu esami coincidano con la stessa ora, pertanto la verifica non viene effettuata
	else 
			return false;
	}
}