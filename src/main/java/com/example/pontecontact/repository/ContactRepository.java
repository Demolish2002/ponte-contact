package com.example.pontecontact.repository;

import com.example.pontecontact.domain.Contact;
import com.example.pontecontact.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> getAllByUser(User user);
}
