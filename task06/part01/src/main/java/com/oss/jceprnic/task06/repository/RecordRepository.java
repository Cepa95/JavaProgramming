package com.oss.jceprnic.task06.repository;

import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT d FROM Device d WHERE d.id = :deviceId")
    Optional<Device> findDeviceById(@Param("deviceId") Long deviceId);

    List<Record> findByYearMeasuredAndDeviceId(Integer yearMeasured, Long deviceId);

    boolean existsByYearMeasuredAndDeviceId(Integer yearMeasured, Long deviceId);

    Optional<Record> findByYearMeasuredAndMonthMeasuredAndDeviceId(Integer yearMeasured, Integer monthMeasured, Long deviceId);
}