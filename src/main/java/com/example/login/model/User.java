package com.example.login.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name="user")
@ToString
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

    @Column(name="email")
    @Email(message = "*Please prived a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name="password")
    @Length(min=5, message="*Your pasword must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;


    @Column(name="name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name="active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @Builder
    public User(int id, String email, String name, String password, Set<Role> roles, int active){
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }



}
