package com.example.demo.controller.registration;

import com.example.demo.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by marta on 08.07.17.
 */
@Component
public class RegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Registration.first.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Registration.last.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "Registration.birthday.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Registration.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Registration.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "Registration.password.confirmation.empty");


    }
}
