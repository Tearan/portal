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

    public enum Status{
        NEW, ACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private Status status;

    @NotNull
    private String name;

    @NotNull
    private String login;

    @Column(unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String passwordHash;

    @NotNull
    private Date birthday;

    private String token;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="User_Role",
            joinColumns=
            @JoinColumn(name="User_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Role_id", referencedColumnName="id")
    )
    private List<Role> roles;

}
