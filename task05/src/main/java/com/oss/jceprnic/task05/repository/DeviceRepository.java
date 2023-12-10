package com.oss.jceprnic.task05.repository;


import com.oss.jceprnic.task05.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByName(String name);

    @Query("SELECT d FROM Device d LEFT JOIN FETCH d.records r WHERE d.id = :deviceId")
    Optional<Device> findByIdWithRecords(@Param("deviceId") Long deviceId);

}
