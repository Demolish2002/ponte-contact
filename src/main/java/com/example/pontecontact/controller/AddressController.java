package com.example.pontecontact.controller;

import com.example.pontecontact.dto.incoming.AddressCreationCommand;
import com.example.pontecontact.dto.outgoing.AddressListItem;
import com.example.pontecontact.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createNewAddress(@RequestBody AddressCreationCommand command){


        addressService.createNewAddress(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@RequestBody AddressCreationCommand command, @PathVariable Long id){
        addressService.updateAddressById(id,command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<AddressListItem>> getAllAddressesByContact(@PathVariable Long id){


        return new ResponseEntity<>(addressService.getAllByContact(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/forUpdate/{id}")
    public ResponseEntity<AddressListItem> getAddressById(@PathVariable Long id){


        return new ResponseEntity<>(addressService.getAddressById(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id){
        addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
