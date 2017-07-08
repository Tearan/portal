package com.example.demo.rest;

import com.example.demo.bean.User;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marta on 03.06.17.
 */
@RestController
public class UserController {


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<User> showUser(ModelMap model){
        User user = new User();
        user.setName("ALA");
        return ResponseEntity.ok(user);

    }
}
