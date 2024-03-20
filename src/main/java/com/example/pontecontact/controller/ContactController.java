package com.example.pontecontact.controller;

import com.example.pontecontact.dto.incoming.ContactCreationCommand;
import com.example.pontecontact.dto.outgoing.ContactListItem;
import com.example.pontecontact.service.ContactService;
import com.example.pontecontact.validator.ContactCreationCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private ContactService contactService;
    private ContactCreationCommandValidator validator;
    @Autowired
    public ContactController(ContactService contactService, ContactCreationCommandValidator validator) {
        this.contactService = contactService;
        this.validator = validator;
    }
    @InitBinder("contactCreatonCommand")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validator);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Long> createNewContact(@RequestBody ContactCreationCommand command){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.UserDetails loggedInUser = (User) authentication.getPrincipal();

        Long contactId=contactService.createContact(loggedInUser.getUsername(),command);
        return new ResponseEntity<>(contactId,HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactInfosById(@PathVariable Long id){
        contactService.deleteContactInfosById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContactListItem>> getAllContactsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.UserDetails loggedInUser = (User) authentication.getPrincipal();
        System.out.println("entered");
        return new ResponseEntity<>(contactService.getAllContactByUser(loggedInUser.getUsername()),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContactById(@RequestBody ContactCreationCommand command,@PathVariable Long id){
        contactService.updateContactById(command,id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{contactId}/phoneNumber/{phoneNumberId}")
    public ResponseEntity<Void> deleteEmailByContactAndId(@PathVariable Long contactId,@PathVariable Long phoneNumberId){
        System.out.println("entered");
        contactService.deleteEmailById(contactId,phoneNumberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{id}/phoneNumber")
    public ResponseEntity<Void> addPhoneNumber(@RequestBody String phoneNumber,@PathVariable Long id){
        contactService.addEmailToContacts(phoneNumber,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ContactListItem> getContactById(@PathVariable Long id){
        return new ResponseEntity<>(contactService.getContactById(id),HttpStatus.OK);
    }
}
