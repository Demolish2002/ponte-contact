package com.example.pontecontact.controller;

import com.example.pontecontact.dto.incoming.AddressCreationCommand;
import com.example.pontecontact.dto.outgoing.AddressListItem;
import com.example.pontecontact.service.AddressService;
import com.example.pontecontact.validator.AddressCreationCommandValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private AddressService addressService;
    private AddressCreationCommandValidator validator;
    @Autowired
    public AddressController(AddressService addressService, AddressCreationCommandValidator validator) {
        this.addressService = addressService;
        this.validator = validator;
    }
    @InitBinder("addressCreationCommand")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validator);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createNewAddress(@RequestBody @Valid AddressCreationCommand command){


        addressService.createNewAddress(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@RequestBody @Valid AddressCreationCommand command, @PathVariable Long id){
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
