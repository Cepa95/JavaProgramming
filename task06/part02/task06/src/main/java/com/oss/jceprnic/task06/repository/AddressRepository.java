package com.oss.jceprnic.task06.repository;

import com.oss.jceprnic.task06.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Record, Long> {
}
