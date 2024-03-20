package com.example.pontecontact.dto.outgoing;

import com.example.pontecontact.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {
    String role;

    public UserDetails(org.springframework.security.core.userdetails.UserDetails user) {
        this.role = user.getAuthorities().toString();
    }
}
