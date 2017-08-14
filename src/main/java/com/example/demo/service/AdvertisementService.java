package com.example.demo.service;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
import com.example.demo.bean.User;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
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

    @Autowired
    private AttachmentService attachmentService;


    public Advertisement addAdvertisement(Advertisement advertisement, List<MultipartFile> attachments){


        User author = userService.getCurrentUser();
        advertisement.setAuthorId(author.getId().toString());
        advertisement.setCreationDate(new Date());

        attachments.stream().forEach(pic -> {

            try {
                Attachment newPic = new Attachment(pic);
                advertisement.addAttachment(newPic);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Advertisement createdAdv = advRepository.save(advertisement);
        return createdAdv;
    }

    public List<Advertisement> findByAuthor(String condition){
        return advRepository.findByAuthorId(condition);
    }

    public List<Advertisement> getAll(){
        return advRepository.findAll();
    }
}
