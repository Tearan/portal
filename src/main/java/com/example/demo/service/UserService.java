package com.example.demo.service;

import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by marta on 08.07.17.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user, String password ){
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setStatus(User.Status.WAITING_CONFIRMATION);
        ArrayList roles = new ArrayList();
        Role newRole = roleRepository.getByName("USER");
        roles.add(newRole);
        user.setRoles(roles);
        repository.save(user);
        //TODO add notifier
        return user;
    }
}
