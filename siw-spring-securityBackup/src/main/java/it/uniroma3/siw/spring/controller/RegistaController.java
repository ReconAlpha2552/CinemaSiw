package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.spring.controller.validator.RegistaValidator;
import it.uniroma3.siw.spring.model.Regista;
import it.uniroma3.siw.spring.service.RegistaService;


@Controller
public class RegistaController {
	
	@Autowired
	private RegistaService registaService;
	
    @Autowired
    private RegistaValidator registaValidator;
        
  
    @RequestMapping(value="/registaForm", method = RequestMethod.GET)
    public String addPrerequisito(Model model) {
    	model.addAttribute("regista", new Regista());
        return "regista/registaForm";
    }
    
    
    @RequestMapping(value = "/registaForm", method = RequestMethod.POST)
    public String addPrerequisito(@ModelAttribute("regista") Regista regista, 
    									Model model, BindingResult bindingResult) {
    	this.registaValidator.validate(regista, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.registaService.inserisci(regista);
            model.addAttribute("regista", this.registaService.tutti());
            return "admin/home";
        }
        return "regista/registaForm";
    }
    
    
}
