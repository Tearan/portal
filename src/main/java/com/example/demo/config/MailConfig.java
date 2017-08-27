package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by marta on 26.08.17.
 */
@Configuration
@PropertySource("classpath:mails.properties")
public class MailConfig {

    @Autowired
    private Environment env;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(env.getProperty("spring.mail.port", Integer.class));

        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth", Boolean.class));
        props.put("mail.smtp.starttls.enable",  env.getProperty("mail.smtp.starttls.enable", Boolean.class));
        props.put("mail.debug", env.getProperty("mail.debug", Boolean.class));
        props.put("smtp.ssl", env.getProperty("smtp.ssl"));

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
