package it.uniroma3.siw.spring.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cinema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String indirizzo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Produttore getProduttore() {
		return produttore;
	}

	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}

	public List<Sala> getSala() {
		return sala;
	}

	public void setSala(List<Sala> sala) {
		this.sala = sala;
	}

	public List<Film> getFilm() {
		return film;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() { 
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setDataIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@OneToOne
	private Produttore produttore ;
	
	@OneToMany
	private List<Sala> sala ;
	
	@ManyToMany
	private List<Film> film ;
	
}
