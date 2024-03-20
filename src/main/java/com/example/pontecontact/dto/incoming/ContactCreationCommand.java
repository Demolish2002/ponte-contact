package com.example.pontecontact.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactCreationCommand {
    private List<String> phoneNumbers;

    private String name;

    private LocalDateTime dateOfBirth;

    private String mothersMaidenName;

    private String tajNumber;

    private String taxIdentification;

    private String email;
}
