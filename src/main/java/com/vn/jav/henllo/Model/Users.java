package com.vn.jav.henllo.Model;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

@Table(name="users")
@Data
@Entity

public class Users implements Serializable {

    @Id

    @Column(name = "phone",unique = true)
    @NotBlank(message = "username is required")
    private String phone;

    @Column(name = "username")
    @NotBlank(message = "username is required")
    private  String username;

    @Column(name = "email",unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email incorrect format")
    private String email;

    @Column(name = "admin")
    private Boolean isAdmin;

    @Column(name = "password")
    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "password at least 6 character")
    private String passwords;
    @Column(name = "img")
    private String img;



}
