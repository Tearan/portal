package com.example.demo.rest;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by marta on 03.06.17.
 */
@Log4j
@RestController
public class UserController {

    private final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers( ){
//        Pageable pageable = new PageRequest(page, size);
        return ResponseEntity.ok(userRepository.findAll());
    }


    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser(){
        User user = userService.getCurrentUser() ;
        LOG.info("getCurrentUser: "+user.getName());
        return ResponseEntity.ok(user);
    }
}
