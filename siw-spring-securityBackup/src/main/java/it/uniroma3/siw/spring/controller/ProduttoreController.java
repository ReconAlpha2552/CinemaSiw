package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.ProduttoreValidator;
import it.uniroma3.siw.spring.model.Film;
import it.uniroma3.siw.spring.model.Produttore;
import it.uniroma3.siw.spring.service.FilmService;
import it.uniroma3.siw.spring.service.ProduttoreService;


@Controller
public class ProduttoreController {
	
	@Autowired
	private ProduttoreService produttoreService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ProduttoreValidator produttoreValidator;
    
    @RequestMapping(value = "/filmAcquisto", method = RequestMethod.GET)
    public String getFilms(Model model) {
    		model.addAttribute("produttore", this.produttoreService.produttorePerId((long) 0));
    		model.addAttribute("films", this.filmService.tutti());
    		return "film/filmAcquisto";
    }
    
    @RequestMapping(value = "/filmAcquistoConferma/{pid}/{fid}", method = RequestMethod.GET)
    public String getFilm(@PathVariable("pid") Long pid,@PathVariable("fid") Long fid, Model model) {
    	model.addAttribute("film", this.filmService.filmPerId(fid));
    	model.addAttribute("produttore", this.produttoreService.produttorePerId((long) 0));
    		return "film/conferma";
    }
    
    @RequestMapping(value = "/confermaFinale/{pid}/{fid}", method = RequestMethod.GET)
    public String confermaFilm(@PathVariable("pid") Long pid,@PathVariable("fid") Long fid, Model model) {
    	
    	Produttore produttore = this.produttoreService.produttorePerId(pid);
    	Film film = this.filmService.filmPerId(fid);
    	
    	if(produttore.getBudget() < film.getCosto()) {
    		
    		
    		return "film/noComprare";
    		
    	}else {
    	
    		produttore.getFilm().add(film);
    		film.setProduttore(produttore);
    		this.produttoreService.inserisci(produttore);
    		this.filmService.inserisci(film);
    		return "film/siComprare";
    	}
    	
    }
    
    
    @RequestMapping(value = "/produttoreModifica", method = RequestMethod.GET)
    public String modificaProduttore(Model model) { //porta al form per la modifica del produttore
    		model.addAttribute("produttore", this.produttoreService.produttorePerId((long) 0));
    		
    		return "cinema/modificaProduttore";
    }
    
    @RequestMapping(value = "/confermaModifica/{id}", method = RequestMethod.POST)
    public String confermaModificaProduttore(@PathVariable("id") Long id,@ModelAttribute("produttore") Produttore  produttore, Model model, BindingResult bindingResult) { 
    		//model.addAttribute("produttore", this.produttoreService.produttorePerId((long) 0));
    	this.produttoreValidator.validate(produttore, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.produttoreService.inserisci(produttore);
            model.addAttribute("film", this.filmService.tutti());
            Produttore originale = produttoreService.produttorePerId(id);
            produttore.setId(originale.getId());
            produttoreService.inserisci(produttore);
            return "admin/home";
        }
        return "cinema/modificaProduttore";
    	
    		
    }
    
    
  /*  @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    public String getFilm(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("film", this.filmService.filmPerId(id));
    	return "film/film";
    }*/
    
    
}
