package com.example.pontecontact.domain;

import com.example.pontecontact.dto.incoming.UserCreationCommand;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Contact> contactList;
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    public User(UserCreationCommand command) {
        this.username = command.getUsername();
        this.password = command.getPassword();
    }
}
