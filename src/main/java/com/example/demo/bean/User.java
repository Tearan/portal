package com.example.demo.bean;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
