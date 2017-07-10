package com.example.demo.bean;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    public enum Role{
        USER, ADMIN
    }

    public enum Status{
        NEW, ACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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

    @ManyToMany
    @JoinTable(name="User_roles",
            joinColumns=
            @JoinColumn(name="User_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Role_id", referencedColumnName="id")
    )
    private List<Role> roles;

//    @OneToMany//TODO ogarnąć
//    private List<Advertisement> advertisements;
}
