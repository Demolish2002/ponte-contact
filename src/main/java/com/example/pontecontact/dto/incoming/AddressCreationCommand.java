package com.example.pontecontact.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressCreationCommand {
    private Long contactId;
    private Integer postalCode;
    private String  city;
    private String street;
    private String houseNumber;
}
