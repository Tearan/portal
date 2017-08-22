package com.example.demo.service;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
import com.example.demo.bean.User;
import com.example.demo.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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


    public Advertisement addAdvertisement(Advertisement advertisement, List<MultipartFile> files){

        //todo It's the best way?
        User author = userService.getCurrentUser();
        advertisement.setAuthorId(author.getId().toString());
        advertisement.setCreationDate(new Date());

        files.stream().forEach(file -> {
            try {
                Attachment newAttachment = new Attachment(file.getOriginalFilename().replace(" ", "-"));
                advertisement.addAttachment(newAttachment);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Advertisement createdAdv = advRepository.save(advertisement);
        attachmentService.saveFileInDirectory(files, createdAdv.getId().toString());
        return createdAdv;
    }

    public List<Advertisement> findByAuthor(String condition){
        return advRepository.findByAuthorId(condition);
    }

    public List<Advertisement> getAll(){
        return advRepository.findAll();
    }
}
