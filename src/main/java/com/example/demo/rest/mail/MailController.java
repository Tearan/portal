package com.example.demo.rest.mail;

import com.example.demo.mail.Notifier;
import groovy.util.logging.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by marta on 26.08.17.
 */
@RestController
@Log4j
public class MailController {

    private static final Logger LOG =  LoggerFactory.getLogger(MailController.class);
    @Autowired
    private Notifier notifier;

    @RequestMapping(value = "/sand", method = RequestMethod.POST)
    public ResponseEntity<?> getCurrentUser(@RequestBody MailRequest mailRequest){
        LOG.info(mailRequest.toString());
        notifier.sandInformationToAuthor(mailRequest);
        return ResponseEntity.ok("{}");
    }
}
