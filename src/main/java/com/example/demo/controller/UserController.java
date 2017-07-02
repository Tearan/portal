package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marta on 03.06.17.
 */
@RestController
public class UserController {


    @RequestMapping(value = "/user**", method = RequestMethod.GET)

    public String showUser(ModelMap model){
        User user = new User();
        
        return "hello";

    }

    @RequestMapping(value = "/admin**",  method = RequestMethod.GET)
    public String showAdmin(ModelMap model){
        User user = new User();

        return "hello admin";

    }
}
