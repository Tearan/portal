package com.example.demo.rest;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marta on 03.06.17.
 */
@Log4j
@RestController
public class UserController {

    private final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<User> showUser(ModelMap model){
        User user = new User();
        user.setName("ALA");
        return ResponseEntity.ok(user);

    }


    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser(){
        User user = userService.getCurrentUser() ;
        LOG.info("getCurrentUser: "+user.getName());
        return ResponseEntity.ok(user);
    }
}
