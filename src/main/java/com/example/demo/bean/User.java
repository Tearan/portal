package com.example.demo.bean;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="users")
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

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private Date birthday;

    private String token;

    private ArrayList<Role> roles;

}
