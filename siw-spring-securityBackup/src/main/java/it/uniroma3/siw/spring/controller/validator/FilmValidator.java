package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.spring.model.Cinema;
import it.uniroma3.siw.spring.model.Film;
import it.uniroma3.siw.spring.service.FilmService;

@Component
public class FilmValidator implements Validator {
	@Autowired
	private FilmService filmService;
	
    private static final Logger logger = LoggerFactory.getLogger(FilmValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genere", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "durata", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "protagonista", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "costo", "required");
		
		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.filmService.alreadyExists((Film)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Cinema.class.equals(aClass);
	}
}
