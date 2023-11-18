package com.oss.jceprnic.task04.service;

import com.oss.jceprnic.task04.model.Device;
import com.oss.jceprnic.task04.model.Record;
import com.oss.jceprnic.task04.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record addRecord(Long deviceId) {
        Device device = recordRepository.findDeviceById(deviceId)
                .orElseThrow(() -> new NoSuchElementException("Device not found with id: " + deviceId));

        Long electricityConsumption = (long) (Math.random() * 500);

        Record record = new Record();
        record.setElectricityConsumptionInKWh(electricityConsumption);
        record.setDevice(device);

        return recordRepository.save(record);
    }




}
