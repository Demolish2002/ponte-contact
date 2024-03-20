package com.example.pontecontacts.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Contact> contactList;
}
