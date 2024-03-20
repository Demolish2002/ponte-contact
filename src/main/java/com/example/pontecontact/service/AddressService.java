package com.example.pontecontact.service;

import com.example.pontecontact.domain.Address;
import com.example.pontecontact.domain.Contact;
import com.example.pontecontact.dto.incoming.AddressCreationCommand;
import com.example.pontecontact.dto.outgoing.AddressListItem;
import com.example.pontecontact.repository.AddressRepository;
import com.example.pontecontact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressService {
    private AddressRepository addressRepository;
    private ContactRepository contactRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, ContactRepository contactRepository) {
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
    }

    public void createNewAddress(AddressCreationCommand command) {
        Address address=new Address(command);
       Optional<Contact>  optContact=contactRepository.findById(command.getContactId());
       if (optContact.isPresent()){
           address.setContact(optContact.get());
           optContact.get().getAddressList().add(address);
       }
        addressRepository.save(address);
    }


    //the entity is already in persistent context therefore no saving line is needed
    public void updateAddressById(Long id, AddressCreationCommand command) {
        Optional<Address> optAddress=addressRepository.findById(id);
        if(optAddress.isPresent()){
            Address address=optAddress.get();
            address.setCity(command.getCity());
            address.setStreet(command.getStreet());
            address.setHouseNumber(command.getHouseNumber());
            address.setPostalCode(command.getPostalCode());
        }

    }

    public void deleteById(Long id) {
        Optional<Address> optAddress=addressRepository.findById(id);
        optAddress.ifPresent(address -> addressRepository.delete(address));
    }

    public List<AddressListItem> getAllByContact(Long id) {
        Contact contact=contactRepository.getById(id);
        return addressRepository.getAllByContact(contact).stream().map(AddressListItem::new).collect(Collectors.toList());
    }

    public AddressListItem getAddressById(Long id) {
        return new AddressListItem(addressRepository.getById(id));
    }

}
