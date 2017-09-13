package com.example.demo.controller.registration;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by marta on 08.07.17.
 */
@Component
@Log4j
public class RegistrationValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Registration.first.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Registration.last.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "Registration.birthday.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Registration.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Registration.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "Registration.password.confirmation.empty");
        Registration registration = (Registration) o;

        if (registration.getEmail() != null) {
            if (!registration.getEmail().matches(EMAIL_PATTERN)) {
                errors.rejectValue("email", "Email.pattern");
            }

            User user = userRepository.findByEmail(registration.getEmail());
            if (user != null) {
                errors.rejectValue("email", "Email.used");
            }
        }

        if (registration.getBirthday() != null) {
            try {
                log.info(registration.getBirthday()+"\n");

                LocalDate birthday = LocalDate.parse(registration.getBirthday(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));


                if (birthday.isAfter(LocalDate.now().minusYears(15))) {
                    errors.rejectValue("birthday", "Registration.birthday.tooYoung");
                }
            } catch (DateTimeParseException e1) {
                errors.rejectValue("birthday", "Registration.birthday.pattern");
            }
        }




    }
}
