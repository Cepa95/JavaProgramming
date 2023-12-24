package com.oss.jceprnic.task06.repository;

import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {


    List<Record> findByDeviceId(Long deviceId);

    @Query("SELECT d FROM Device d WHERE d.id = :deviceId")
    Optional<Device> findDeviceById(@Param("deviceId") Long deviceId);

}
