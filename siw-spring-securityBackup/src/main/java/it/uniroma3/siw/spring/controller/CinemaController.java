package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.service.CinemaService;


@Controller
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
    
	 @RequestMapping(value = "/cinema", method = RequestMethod.GET)
	    public String getFilm(String nome, Model model) {
	    	model.addAttribute("cinema", this.cinemaService.tutti());
	    	return "cinema/cinema";
	    }
    
}
