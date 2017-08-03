package com.example.demo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by marta on 10.07.17.
 */

@Entity
@Data
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

    private String picture;

    @ElementCollection(targetClass=Category.class)
//    @JoinTable(name = "Category", joinColumns = @JoinColumn(name = "Advertisement_id"))
    @Column(name = "Category", nullable = false)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="Advertisement_categories", joinColumns = @JoinColumn(name = "Advertisement_id"))
    private List<Category> categories;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date creationDate;

}
