package com.example.demo.bean;

import com.example.demo.bean.Attachment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marta on 10.07.17.
 */

@Entity
@Getter
@Setter
public class Advertisement {

    public enum Category{
        MOTO, PET, MAN, WOMAN, CLOTHES, JEWELLERY, HOME
    }

    public enum Type{
        BUYING, SALE, EXCHANGE
    }

    public enum Status{
        NEW, CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authorId;

    private String content;

    private String title;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)//TODO do zastanowienia czy potrzeba drugą tabelkę
    @JsonIgnore
    private List<Attachment> pictures = new ArrayList<>();

    @ElementCollection(targetClass=Category.class)
    @Column(name = "Category", nullable = false)//todo zmienić na małe literki
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="Advertisement_categories", joinColumns = @JoinColumn(name = "Advertisement_id"))
    private List<Category> categories;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    private Date creationDate;

    public void addAttachment(Attachment attachment){
            pictures.add(attachment);
            attachment.setAdvertisement(this);
    }

}
