package com.formation.projet.business.forms;

import com.formation.projet.business.beans.Computer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 07/06/13
 * Time: 10:04
 */
@Component
public class ComputerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Computer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");

        Computer computer = (Computer) target;

        // introduced vs discontinued
        Date introduced = computer.getIntroduced();
        Date discontinued = computer.getDiscontinued();
        if (introduced != null && discontinued != null && introduced.after(discontinued)) {
            errors.rejectValue("discontinued", "Should be after introduction date");
        }
    }
}
