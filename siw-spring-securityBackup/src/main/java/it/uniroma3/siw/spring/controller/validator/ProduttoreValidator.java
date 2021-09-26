package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.spring.model.Produttore;
import it.uniroma3.siw.spring.service.ProduttoreService;

@Component
public class ProduttoreValidator implements Validator {
	@Autowired
	private ProduttoreService produttoreService;
	
    private static final Logger logger = LoggerFactory.getLogger(ProduttoreValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "budget", "required");
		
		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.produttoreService.alreadyExists((Produttore)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Produttore.class.equals(aClass);
	}
}
