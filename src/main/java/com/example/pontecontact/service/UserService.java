package com.example.pontecontact.service;

import com.example.pontecontact.domain.User;
import com.example.pontecontact.domain.UserRole;
import com.example.pontecontact.dto.incoming.UserCreationCommand;
import com.example.pontecontact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByUsername(username);
        if(user!=null){
            String role = user.getRole().toString();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .authorities(AuthorityUtils.createAuthorityList(role))
                    .password(user.getPassword())
                    .build();
        }else {
            return null;
        }

    }

    public void registerNewUser(UserCreationCommand command) {
        User userToBeSaved=new User(command);
        userToBeSaved.setRole(UserRole.ROLE_USER);
        userToBeSaved.setPassword(passwordEncoder.encode(command.getPassword()));
        userRepository.save(userToBeSaved);
    }
}
