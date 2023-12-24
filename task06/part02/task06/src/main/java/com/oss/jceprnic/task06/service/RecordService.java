package com.oss.jceprnic.task06.service;

import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.repository.RecordRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> getAllRecordsForDevice(Long deviceId) {
        return recordRepository.findByDeviceId(deviceId);
    }

    public void createRecord(Record record) {
        recordRepository.save(record);
    }

}
