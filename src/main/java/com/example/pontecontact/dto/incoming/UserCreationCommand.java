package com.example.pontecontact.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreationCommand {
    private String username;
    private String password;
}
