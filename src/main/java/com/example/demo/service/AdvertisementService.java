package com.example.demo.service;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.User;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by marta on 28.07.17.
 */
@Component
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advRepository;

    @Autowired
    private UserService userService;

    public Advertisement addAdvertisement(Advertisement advertisement){

        advertisement.setStatus(Advertisement.Status.NEW);
        User author = userService.getCurrentUser();
        advertisement.setAuthorId(author.getId().toString());

        advertisement.setCreationDate(new Date());
        return advRepository.save(advertisement);
    }

    public List<Advertisement> findByAuthor(String condition){
        return advRepository.findByAuthorId(condition);
    }

    public List<Advertisement> getAll(){
        return advRepository.findAll();
    }
}
