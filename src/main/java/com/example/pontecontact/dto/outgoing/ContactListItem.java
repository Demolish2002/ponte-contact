package com.example.pontecontact.dto.outgoing;

import com.example.pontecontact.domain.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactListItem {
    private Long id;
    private List<String> phoneNumbers;

    private String name;

    private String dateOfBirth;

    private String mothersMaidenName;

    private String tajNumber;

    private String taxIdentification;

    private String email;

    public ContactListItem(Contact contact) {
        this.id= contact.getId();
        this.phoneNumbers = contact.getPhoneNumbers();
        this.name = contact.getName();
        this.dateOfBirth = contact.getDateOfBirth().toString();
        this.mothersMaidenName = contact.getMothersMaidenName();
        this.tajNumber = contact.getTajNumber();
        this.taxIdentification = contact.getTaxIdentification();
        this.email = contact.getEmail();
    }
}
