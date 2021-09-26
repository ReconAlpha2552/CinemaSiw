package it.uniroma3.siw.spring.controller;

import java.util.ArrayList;
import java.util.List;

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
import it.uniroma3.siw.spring.controller.validator.SalaValidator;
import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.service.SalaService;


@Controller
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
    @Autowired
    private SalaValidator salaValidator;
        
  
    @RequestMapping(value="/salaForm", method = RequestMethod.GET)
    public String addPrerequisito(Model model) {
    	model.addAttribute("sala", new Sala());
        return "sala/salaForm";
    }
    
    @RequestMapping(value = "/salaForm", method = RequestMethod.POST)
    public String addPrerequisito(@ModelAttribute("sala") Sala sala, 
    									Model model, BindingResult bindingResult) {
    	this.salaValidator.validate(sala, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.salaService.inserisci(sala);
            model.addAttribute("sala", this.salaService.tutti());
            return "admin/home";
        }
        return "sala/salaForm";
    }
    
    
    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String getSale(Model model) {
    		model.addAttribute("sale", this.salaService.tutti());
    		return "sala/sale";
    }
    
    
    @RequestMapping(value = "/sala/{id}", method = RequestMethod.GET)
    public String getSala(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("sala", this.salaService.salaPerId(id));
    	return "sala/sala";
    }
    
    
}
