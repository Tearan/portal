package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by marta on 08.07.17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user, String password ){
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setStatus(User.Status.NEW);
        ArrayList roles = new ArrayList();
        roles.add(User.Role.USER);
        user.setRoles(roles);
        repository.save(user);
        //TODO add notifier
        return user;
    }
}
