package com.example.demo.rest;

import com.example.demo.bean.Advertisement;
import com.example.demo.bean.Attachment;
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
import java.util.ArrayList;
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
    private AdvertisementService advService;


    @RequestMapping( method = RequestMethod.POST, consumes = {"application/octet-stream", "multipart/mixed", "multipart/form-data" })
    public ResponseEntity<Advertisement> create(
            @RequestPart(value = "pictures") List<MultipartFile> files,
            @RequestPart(value = "advertisement") Advertisement advertisement) throws IOException {

        Advertisement savedAdv = advService.addAdvertisement(advertisement, files);

        return ResponseEntity.ok(savedAdv);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAdvertisements(@PathVariable("id") String condition){
        LOG.info("getAdvertisements for userId: "+condition);
        List<Advertisement> list = advService.findByAuthor(condition);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Status>> getStatuses(){
        List<Advertisement.Status> statuses = Arrays.asList(Advertisement.Status.values());
         return ResponseEntity.ok(statuses);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Type>> getTypes(){
        List<Advertisement.Type> types = Arrays.asList(Advertisement.Type.values());
        return ResponseEntity.ok(types);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAll(){
        List<Advertisement> adv = advService.getAll();
        return ResponseEntity.ok(adv);
    }


}
