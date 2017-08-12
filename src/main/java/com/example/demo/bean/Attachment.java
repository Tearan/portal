package com.example.demo.bean;

import lombok.Builder;

import javax.persistence.*;

/**
 * Created by marta on 05.08.17.
 */
@Builder
@Entity
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="picture_content", nullable=false)
    private byte[] picture_content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Advertisement advertisement;
}
