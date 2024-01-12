package com.oss.jceprnic.task06.service;

import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> getAllRecordsForDevice(Long deviceId) {
        return recordRepository.findByDeviceId(deviceId);
    }

    public void createRecord(Record record, Long deviceId) {
        Device device = recordRepository.findDeviceById(deviceId)
                .orElseThrow(() -> new NoSuchElementException("Device not found with id: " + deviceId));

        record.setDevice(device);

        recordRepository.save(record);
    }

}
