package com.example.demo.service;

import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by marta on 08.07.17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
        userRepository.save(user);
        //TODO add notifier
        return user;
    }

    public  User getCurrentUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(email);
        return currentUser;
    }

    public User addFriendToUser(User user,Long friendId){
        user.getFriends().add(userRepository.findById(friendId));
        return userRepository.save(user);
    }

}
