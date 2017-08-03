package com.example.demo.rest;

import com.example.demo.bean.Advertisement;
import com.example.demo.service.AdvertisementService;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marta on 28.07.17.
 */
@Log4j
@RestController
public class AdvertisementController {
    private final Logger LOG = Logger.getLogger(AdvertisementController.class);


    @Autowired
    private AdvertisementService advService;

    @RequestMapping(value = "/adv", method = RequestMethod.POST)
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement advertisement){
        Advertisement newAdv = advService.addAdvertisement(advertisement);
        return ResponseEntity.ok(newAdv);
    }

    @RequestMapping(value = "/adv/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAdvertisements(@PathVariable("id") String condition){
        LOG.info("getAdvertisements for userId: "+condition);
        List<Advertisement> list = advService.findByAuthor(condition);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/adv/statuses", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Status>> getStatuses(){
        List<Advertisement.Status> statuses = Arrays.asList(Advertisement.Status.values());
         return ResponseEntity.ok(statuses);
    }

    @RequestMapping(value = "/adv/types", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement.Type>> getTypes(){
        List<Advertisement.Type> types = Arrays.asList(Advertisement.Type.values());
        return ResponseEntity.ok(types);
    }

    @RequestMapping(value = "/adv/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisement>> getAll(){
        List<Advertisement> adv = advService.getAll();
        return ResponseEntity.ok(adv);
    }


}
