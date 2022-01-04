package com.example.kingsta.domain.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String userName;
    private String email;
    private String password;

    private String role;

}
