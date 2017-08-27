package com.example.demo.rest.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by marta on 26.08.17.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {

    private String subject;
    private String content;
    private String from;
    private String to;

}
