package com.example.demo.controller.registration;

import com.sun.javafx.applet.ExperimentalExtensions;
import javafx.util.Builder;
import org.apache.log4j.Logger;
import com.example.demo.bean.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by marta on 08.07.17.
 */
@Controller
public class RegistrationController {

    private static final Logger LOG = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator validator;

    @RequestMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("registration", new Registration());

        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegistration(Registration registration, Model model, BindingResult errors,
                                   RedirectAttributes redirectAttributes){

        validator.validate(registration, errors);

        if(errors.hasErrors()){
            redirectAttributes.addFlashAttribute("warn", "Errors occurred");
            LOG.error(errors.getAllErrors());
            return "register";
        }else{
            userService.createUser(registration.getUser(), registration.getPassword());
            redirectAttributes.addFlashAttribute("info", "Successful register");

            return "redirect:/login";
        }
    }
}
