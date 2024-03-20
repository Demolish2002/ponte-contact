package com.example.pontecontact.controller;

import com.example.pontecontact.dto.incoming.UserCreationCommand;
import com.example.pontecontact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("permitAll")
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserCreationCommand command){
       userService.registerNewUser(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("permitAll")
    @GetMapping("/me")
    public ResponseEntity<com.example.pontecontact.dto.outgoing.UserDetails> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.UserDetails loggedInUser = (User) authentication.getPrincipal();


            if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails) {
                org.springframework.security.core.userdetails.UserDetails user = (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
                return new ResponseEntity<>(new com.example.pontecontact.dto.outgoing.UserDetails(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

    }


}
