package com.example.pontecontact.repository;

import com.example.pontecontact.domain.Address;
import com.example.pontecontact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> getAllByContact(Contact contact);


}
