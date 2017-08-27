package com.example.demo.mail;

import groovy.util.logging.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;


/**
 * Created by marta on 27.08.17.
 */
@Component
@Log4j
public class MailServiceImpl{

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.encoding}")
    private String encoding;

    public void sendEmail(String  subject, String text,  String... to) {

        MimeMessagePreparator preparator = getMessagePreparator(subject, text, to);
        LOG.info(from);
        try {
            mailSender.send(preparator);
            System.out.println("Message Send to" + to);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }



    private MimeMessagePreparator getMessagePreparator(String  subject, String text, String... to) {

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, encoding);
            messageHelper.setTo(to);
            LOG.info(from);
            LOG.info(to[0]);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
        };
        return preparator;
    }
}
