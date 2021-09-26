package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String titolo;
	@Column(nullable = false)
	private String genere;
	@Column(nullable = false)
	private String durata;
	@Column(nullable = false)
	private String protagonista;
	@Column(nullable = false)
	private int costo;
	
	public Long getId() {
		return id;
	}
	
	public Produttore getProduttore() {
		return produttore;
	}

	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}

	public List<Cinema> getCinema() {
		return cinema;
	}

	public void setCinema(List<Cinema> cinema) {
		this.cinema = cinema;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getCognome() {
		return genere;
	}
	

	public void setCognome(String genere) {
		this.genere = genere;
	}
	
	public String getDurata() {
		return durata;
	}
	

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}
	
	public String getProtagonista() {
		return protagonista;
	}
	

	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}
	
	public int getCosto() {
		return costo;
	}
	

	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	@ManyToOne(cascade={CascadeType.REMOVE})
	private Produttore produttore ;
	
	@ManyToMany(cascade={CascadeType.REMOVE})
	private List<Cinema> cinema ;
	
	
	@OneToOne(cascade={CascadeType.REMOVE})
	private Sala sala ;
}
