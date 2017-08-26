package com.example.demo.service;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
import com.example.demo.bean.User;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.UserRepository;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
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
@Log4j
public class AdvertisementService {

    private static Logger LOG = Logger.getLogger(AdvertisementService.class);

    @Autowired
    private AdvertisementRepository advRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserRepository userRepository;


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

    public void addWatch(Long idAdv, Long userId) {
        User user = userRepository.findById(userId);
        List<Advertisement> lis = user.getListWatchedAdvertisements();
        lis.add(advRepository.findById(idAdv));

        userRepository.save(user);
    }

    public void removeWatch(Long idAdv, Long userId) {
        User user = userRepository.findById(userId);
        List<Advertisement> lis = user.getListWatchedAdvertisements();
        lis.remove(advRepository.findById(idAdv));

        userRepository.save(user);
    }

    public boolean userWatchThis(Long adId, Long userId) {
        User user = userRepository.findById(userId);
        Advertisement searchedAd = advRepository.findById(adId);
        List<Advertisement> lis = user.getListWatchedAdvertisements();

        return lis.contains(searchedAd);
    }
}
