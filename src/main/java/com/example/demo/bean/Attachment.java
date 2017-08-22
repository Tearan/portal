package com.example.demo.bean;

import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="advertisement_id")
    private Advertisement advertisement;

    public Attachment(String fileName) throws IOException {
        this.name = fileName;
    }

}
