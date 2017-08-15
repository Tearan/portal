package com.example.demo.rest;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.service.AdvertisementService;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    private AttachmentRepository attachmentRepository;


    @RequestMapping( method = RequestMethod.POST, consumes = {"application/octet-stream", "multipart/mixed", "multipart/form-data" })
    public ResponseEntity<Advertisement> create(
            @RequestPart(value = "pictures") List<MultipartFile> files,
            @RequestPart(value = "advertisement") Advertisement advertisement) throws IOException {

        Advertisement savedAdv = advertisementService.addAdvertisement(advertisement, files);

        return ResponseEntity.ok(savedAdv);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAdvertisements(@PathVariable("id") String condition){
        LOG.info("getAdvertisements for userId: "+condition);
        List<Advertisement> list = advertisementService.findByAuthor(condition);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Status>> getStatuses(){
        List<Advertisement.Status> statuses = Arrays.asList(Advertisement.Status.values());
         return ResponseEntity.ok(statuses);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Advertisement> getDetails(@PathVariable("id") String id ){
        Advertisement advertisement = advertisementRepository.findById(Long.parseLong(id));
//        advertisement.setPictures(attachmentRepository.findByAdvertisement_Id(Long.parseLong(id)));
        LOG.info(advertisement);
        return ResponseEntity.ok(advertisement);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Type>> getTypes(){
        List<Advertisement.Type> types = Arrays.asList(Advertisement.Type.values());
        return ResponseEntity.ok(types);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAll(){
        List<Advertisement> adv = advertisementService.getAll();
        return ResponseEntity.ok(adv);
    }


}
