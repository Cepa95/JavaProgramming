package com.oss.jceprnic.task05.service;

import com.oss.jceprnic.task05.model.Device;
import com.oss.jceprnic.task05.model.Record;
import com.oss.jceprnic.task05.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Record updateRecord(Long recordId, Record updatedRecord) {
        Record existingRecord = recordRepository.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Record not found with id: " + recordId));


        existingRecord.setElectricityConsumptionInKWh(updatedRecord.getElectricityConsumptionInKWh());
        existingRecord.setMonthMeasured(updatedRecord.getMonthMeasured());
        existingRecord.setYearMeasured(updatedRecord.getYearMeasured());

        return recordRepository.save(existingRecord);
    }

    public void deleteRecord(Long recordId) {

        recordRepository.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Record not found with id: " + recordId));

        recordRepository.deleteById(recordId);
    }

    public Long getTotalConsumptionForYear(Integer yearMeasured, Long deviceId) {
        List<Record> recordsForYearAndDevice = recordRepository.findByYearMeasuredAndDeviceId(yearMeasured, deviceId);

        if (!recordRepository.existsByYearMeasuredAndDeviceId(yearMeasured, deviceId)) {
            throw new NoSuchElementException("Record not found for the specified year and device ID.");
        }
        return recordsForYearAndDevice.stream()
                .mapToLong(Record::getElectricityConsumptionInKWh)
                .sum();
    }

}
