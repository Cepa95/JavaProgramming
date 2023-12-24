package com.oss.jceprnic.task06.repository;

import com.oss.jceprnic.task06.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityIgnoreCase(String City);


}
