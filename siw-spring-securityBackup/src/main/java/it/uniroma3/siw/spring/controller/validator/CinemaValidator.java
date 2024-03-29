package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Cinema;
import it.uniroma3.siw.spring.service.CinemaService;

@Component
public class CinemaValidator implements Validator {
	@Autowired
	private CinemaService cinemaService;
	
    private static final Logger logger = LoggerFactory.getLogger(CinemaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.cinemaService.alreadyExists((Cinema)o)) {
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
