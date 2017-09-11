package com.example.demo.bean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;

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

    public enum Status {
        WAITING_CONFIRMATION, ACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Enumerated(EnumType.STRING)
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns =
            @JoinColumn(name = "User_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "Role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Watched_Ad",
            joinColumns =
            @JoinColumn(name = "User_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "Advertisement_id", referencedColumnName = "id")
    )
    private List<Advertisement> listWatchedAdvertisements = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Friend",
            joinColumns =
            @JoinColumn(name = "User_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "User_Friend_id", referencedColumnName = "id")
    )
    private List<User> friends = new ArrayList<>();
}
