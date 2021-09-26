package it.uniroma3.siw.spring.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Regista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	private String masterpiece;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() { 
		return nome;
	}
	
	public void setMasterpiece(String masterpiece) {
		this.masterpiece = masterpiece;
	}


	public String getMasterpiece() {
		return masterpiece;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	
	public List<Film> getFilm() {
		return film;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	@OneToMany
	private List<Film> film ;
	
}
