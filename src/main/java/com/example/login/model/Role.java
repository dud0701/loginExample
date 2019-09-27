package com.example.login.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Column(name="role")
    private String role;

    @Builder
    public Role(int id, String role){
        this.id = id;
        this.role = role;
    }
}
