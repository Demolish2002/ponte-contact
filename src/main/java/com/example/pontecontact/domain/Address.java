package com.example.pontecontacts.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer postalCode;

    private String city;
    private String street;
    private String houseNumber;
    @ManyToOne
    private Contact contact;
}
