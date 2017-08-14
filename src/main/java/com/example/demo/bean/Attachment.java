package com.example.demo.bean;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

/**
 * Created by marta on 05.08.17.
 */
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column( nullable=false)
    private byte[] picture_content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="advertisement_id")
    private Advertisement advertisement;

    public Attachment(MultipartFile attach) throws IOException {
        this.picture_content = attach.getBytes();
        this.name = attach.getOriginalFilename();
    }
}
