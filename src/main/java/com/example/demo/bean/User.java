package com.example.demo.bean;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    public enum Status{
        NEW, ACTIVE
    }
    public enum Role{
        USER, ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userId;

    private Status status;

    @NotNull
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String passwordHash;

    @NotNull
    private Date birthday;

    private String token;

    private ArrayList<Role> roles;

}
