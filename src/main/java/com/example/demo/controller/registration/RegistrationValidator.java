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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "registration.required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "registration.required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "registration.required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "registration.required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "registration.required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "registration.required.fieldy");

        Registration registration = (Registration) o;

        if(registration.getEmail() != null){

        }



    }
}
