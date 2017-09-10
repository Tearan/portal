package com.example.demo.security;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by marta on 10.09.17.
 */

@Component
public class UserAuthenticator {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate( String email, String password){

        User user = repository.findByEmail(email);

        if(user != null && passwordEncoder.matches(password, user.getPasswordHash())){
            return user;
        }

        return null;
    }


}
