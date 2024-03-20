package com.example.pontecontact.service;

import com.example.pontecontact.domain.Address;
import com.example.pontecontact.domain.Contact;
import com.example.pontecontact.domain.User;
import com.example.pontecontact.dto.incoming.ContactCreationCommand;
import com.example.pontecontact.dto.outgoing.ContactListItem;
import com.example.pontecontact.repository.AddressRepository;
import com.example.pontecontact.repository.ContactRepository;
import com.example.pontecontact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public Long createContact(String username, ContactCreationCommand command) {
        User user=userRepository.findUserByUsername(username);
        Contact contact=new Contact(command);
        user.getContactList().add(contact);
        contact.setUser(user);
        Contact savedContact= contactRepository.save(contact);
        return savedContact.getId();
    }

    public void deleteContactInfosById(Long id) {
        Contact contact=contactRepository.getById(id);
        List<Address> addresses=addressRepository.getAllByContact(contact);
        addressRepository.deleteAll(addresses);
        contactRepository.delete(contact);
    }

    public List<ContactListItem> getAllContactByUser(String username) {
        User user=userRepository.findUserByUsername(username);
        return contactRepository.getAllByUser(user).stream().map(ContactListItem::new).collect(Collectors.toList());
    }

    public void updateContactById(ContactCreationCommand command, Long id) {
        Optional<Contact> optContact=contactRepository.findById(id);
        if (optContact.isPresent()){
            Contact contact=optContact.get();
            contact.setPhoneNumbers(command.getPhoneNumbers());
            contact.setEmail(command.getEmail());
            contact.setName(command.getName());
            contact.setMothersMaidenName(command.getMothersMaidenName());
            contact.setDateOfBirth(command.getDateOfBirth());
            contact.setTajNumber(command.getTajNumber());
            contact.setTaxIdentification(command.getTaxIdentification());

        }
    }

    public void deleteEmailById(Long contactId, Long phoneNumberId) {
        Optional<Contact> optContact=contactRepository.findById(contactId);
        if (optContact.isPresent()){
            Contact contact=optContact.get();
            int index= Math.toIntExact(phoneNumberId);
            System.out.println("entered");
            System.out.println(contact.getPhoneNumbers().get(index));
            contact.getPhoneNumbers().remove(index);
        }
    }

    public void addEmailToContacts(String phoneNumber, Long id) {
        Optional<Contact> optContact=contactRepository.findById(id);
        if (optContact.isPresent()) {
            Contact contact=optContact.get();
            contact.getPhoneNumbers().add(phoneNumber);
        }
    }

    public ContactListItem getContactById(Long id) {
        return new ContactListItem(contactRepository.getById(id));
    }
}
