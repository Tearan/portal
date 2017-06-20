package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marta on 03.06.17.
 */
@RestController
public class UserController {


    @RequestMapping("/User")
    public String showUser(ModelMap model){
        User user = new User();
        
        return "hello";

    }
}
