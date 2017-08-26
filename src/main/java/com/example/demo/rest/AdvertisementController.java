package com.example.demo.rest;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
import com.example.demo.bean.User;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.service.AdvertisementService;
import com.example.demo.service.AttachmentService;
import com.example.demo.service.UserService;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marta on 28.07.17.
 */
@Log4j
@RepositoryRestController
@RequestMapping(value = "/adv")//necessary for  @RepositoryRestController
public class AdvertisementController {

    private final Logger LOG = Logger.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private UserService userService;


    @RequestMapping( method = RequestMethod.POST, consumes = {"application/octet-stream", "multipart/mixed", "multipart/form-data" })
    public ResponseEntity<Advertisement> create(
            @RequestPart(value = "pictures") List<MultipartFile> files,
            @RequestPart(value = "advertisement") Advertisement advertisement) throws IOException {

        Advertisement savedAdv = advertisementService.addAdvertisement(advertisement, files);

        return ResponseEntity.ok(savedAdv);
    }

    //todo remove id
    @RequestMapping(value = "/me/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAdvertisements(@PathVariable("id") String condition){
        LOG.info("getAdvertisements for userId: "+condition);
        List<Advertisement> list = advertisementRepository.findByAuthorId(condition);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Status>> getStatuses(){
         return ResponseEntity.ok(Arrays.asList(Advertisement.Status.values()));
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdvertisementResponse> getDetails(@PathVariable("id") String id ){
        AdvertisementResponse response = new AdvertisementResponse();
        Advertisement advertisement = advertisementRepository.findById(Long.parseLong(id));
        response.setAdvertisement(advertisement);
        List<String> listFilesName = advertisement.getPictures()
                .stream()
                .map(Attachment::getName)
                .collect(Collectors.toList());

        //todo change!!!!!!

        response.setFiles(listFilesName);
        LOG.info(advertisement);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Type>> getTypes(){
        return ResponseEntity.ok(new ArrayList<>(Arrays.asList(Advertisement.Type.values())));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAll(){
        List<Advertisement> adv = advertisementRepository.findAll();
        return ResponseEntity.ok(adv);
    }


    @RequestMapping(value = "/watch/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> addWatch(@PathVariable("id") String id){
        Long currentUserId = userService.getCurrentUser().getId();
        advertisementService.addWatch(Long.parseLong(id), currentUserId);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/unwatch/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> unwatch(@PathVariable("id") String id){
        Long currentUserId = userService.getCurrentUser().getId();
        advertisementService.removeWatch(Long.parseLong(id), currentUserId);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/watched/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> iWatchThis(@PathVariable("id") String id){
        IWatchResponse response = new IWatchResponse();
        Long currentUserId = userService.getCurrentUser().getId();
        response.setResult(advertisementService.userWatchThis(Long.parseLong(id), currentUserId));
        return ResponseEntity.ok(response);
    }
}
