package com.example.pontecontact.dto.outgoing;

import com.example.pontecontact.domain.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressListItem {
    private Long addressId;
    private Integer postalCode;
    private String  city;
    private String street;
    private String houseNumber;

    public AddressListItem(Address address) {
        this.addressId = address.getId();
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
    }
}
