package com.vn.jav.henllo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

@Table(name="users")
@Entity

public class Users {

    @Id
    @Column(name = "phone")
    private String phone;
    @Column(name = "username")
    private  String username;
    @Column(name = "email")
    private String email;
    @Column(name = "admin")
    private Boolean isAdmin;
    @Column(name = "password")
    private String passwords;
}
