package com.oss.jceprnic.task04.repository;

import com.oss.jceprnic.task04.model.Client;
import com.oss.jceprnic.task04.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByAddressId(Long Id);

}
