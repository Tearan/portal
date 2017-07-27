package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marta on 07.07.17.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(){

        return "login";
    }
}
