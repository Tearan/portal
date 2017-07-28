package com.example.demo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marta on 10.07.17.
 */

@Entity
@Data
public class Advertisement {

    private enum Category{
        MOTO, PET, MAN, WOMAN, CLOTHES, JEWELLERY, HOME
    }

    private enum Type{
        BUYING, SALE, EXCHANGE
    }

    @Id
    @GeneratedValue
    private Long id;

    private String author_id;

    private String content;

    private String title;

    private String picture;

    @ElementCollection(targetClass=Category.class)
//    @JoinTable(name = "Category", joinColumns = @JoinColumn(name = "Advertisement_id"))
    @Column(name = "Category", nullable = false)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="Advertisement_categories", joinColumns = @JoinColumn(name = "Advertisement_id"))
    private List<Category> categories;

    private Type type;

    private String status;

    private String creationData;

}
