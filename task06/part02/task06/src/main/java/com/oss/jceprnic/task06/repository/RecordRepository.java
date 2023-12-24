package com.oss.jceprnic.task06.repository;

import com.oss.jceprnic.task06.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByDeviceId(Long deviceId);

}
