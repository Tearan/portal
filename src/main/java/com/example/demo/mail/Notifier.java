package com.example.demo.mail;

import com.example.demo.rest.mail.MailRequest;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by marta on 26.08.17.
 */
@Component
@Log4j
public class Notifier {

    @Autowired
    private MailServiceImpl mailService;

    public void sandInformationToAuthor(MailRequest mailRequest){
        sand(mailRequest.getSubject(), mailRequest.getContent(), mailRequest.getTo());
    }


    private void sand(String subject, String text ,String... to){
        //TODO add text from velocity
        mailService.sendEmail(subject, text, to);
    }
}
