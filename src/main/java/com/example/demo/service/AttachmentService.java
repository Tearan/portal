package com.example.demo.service;

import com.example.demo.bean.Attachment;
import com.example.demo.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by marta on 12.08.17.
 */
@Component
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment save(MultipartFile file) throws IOException {

        return attachmentRepository.save(Attachment.builder()
                .name(file.getOriginalFilename())
                .picture_content(file.getBytes())
                .build());
    }

}
