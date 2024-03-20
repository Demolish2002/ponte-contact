package com.example.pontecontacts.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "contact")
    private List<Address> addressList;
    @ElementCollection
    private List<String> phoneNumbers;

    private String name;

    private LocalDateTime dateOfBirth;

    private String mothersMaidenName;

    private String tajNumber;

    private String taxIdentification;
    @Email
    private String email;

    @ManyToOne
    private User user;
}
