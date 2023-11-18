package com.oss.jceprnic.task04.repository;

import com.oss.jceprnic.task04.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {

}