package com.example.pontecontact.domain;

import com.example.pontecontact.dto.incoming.ContactCreationCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "contact")
    private List<Address> addressList=new ArrayList<>();

    @ElementCollection
    private List<String> phoneNumbers=new ArrayList<>();

    private String name;

    private LocalDateTime dateOfBirth;

    private String mothersMaidenName;

    private String tajNumber;

    private String taxIdentification;

    private String email;

    @ManyToOne
    private User user;

    public Contact(ContactCreationCommand command) {
        this.phoneNumbers=command.getPhoneNumbers();
        this.name = command.getName();
        this.dateOfBirth = command.getDateOfBirth();
        this.mothersMaidenName = command.getMothersMaidenName();
        this.tajNumber = command.getTajNumber();
        this.taxIdentification = command.getTaxIdentification();
        this.email = command.getEmail();
    }
}
