package com.example.pontecontact.domain;

import com.example.pontecontact.dto.incoming.AddressCreationCommand;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public Address(AddressCreationCommand command) {
        this.postalCode = command.getPostalCode();
        this.city = command.getCity();
        this.street = command.getStreet();
        this.houseNumber = command.getHouseNumber();
    }


}
