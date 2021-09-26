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
import it.uniroma3.siw.spring.controller.validator.FilmValidator;
import it.uniroma3.siw.spring.model.Cinema;
import it.uniroma3.siw.spring.model.Film;
import it.uniroma3.siw.spring.model.Produttore;
import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.service.CinemaService;
import it.uniroma3.siw.spring.service.FilmService;
import it.uniroma3.siw.spring.service.ProduttoreService;


@Controller
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private ProduttoreService produttoreService;
	
    @Autowired
    private FilmValidator filmValidator;
        
  
    @RequestMapping(value="/filmForm", method = RequestMethod.GET)
    public String addPrerequisito(Model model) {
    	model.addAttribute("film", new Film());
        return "film/filmForm";
    }
    
    
    @RequestMapping(value = "/filmForm", method = RequestMethod.POST)
    public String addPrerequisito(@ModelAttribute("film") Film film, 
    									Model model, BindingResult bindingResult) {
    	this.filmValidator.validate(film, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.filmService.inserisci(film);
            model.addAttribute("film", this.filmService.tutti());
            return "admin/home";
        }
        return "film/filmForm";
    }
    
    
    
    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public String getFilms(Model model) {
    		model.addAttribute("films", this.filmService.tutti());
    		return "film/films";
    }
    
    
    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    public String getFilm(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("film", this.filmService.filmPerId(id));
    	return "film/film";
    }
    
    
    @RequestMapping(value="/filmElimina", method = RequestMethod.GET)
    public String elFilms(Model model) {
    	model.addAttribute("films", this.filmService.tutti());
        return "film/filmElimina";
    }
    
    @RequestMapping(value = "/filmElimina/{id}", method = RequestMethod.GET)
    public String elFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.filmPerId(id);
		this.filmService.elimina(film);
    	return "admin/home";
    }
    
    
}
