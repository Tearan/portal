package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marta on 09.07.17.
 */
@Getter
@Setter
@Entity
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    @Override
    public String getAuthority() {
        return this.name;
    }
}
