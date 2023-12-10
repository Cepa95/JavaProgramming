package com.oss.jceprnic.task05.controller;

import com.oss.jceprnic.task05.model.Record;
import com.oss.jceprnic.task05.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/records")
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;

    //http://localhost:8080/api/records
    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    //http://localhost:8080/api/records/add?deviceId=3
    @PostMapping("/add")
    public Record addRecord(@RequestParam Long deviceId) {
        return recordService.addRecord(deviceId);
    }

    // http://localhost:8080/api/records/1

    //{
    //  "electricityConsumptionInKWh": 150,
    //  "monthMeasured": 9,
    //  "yearMeasured": 2023
    //}
    @PutMapping("/{recordId}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long recordId, @RequestBody Record updatedRecord) {
        try {
            Record updated = recordService.updateRecord(recordId, updatedRecord);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/records/1
    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long recordId) {
        try {
            recordService.deleteRecord(recordId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/records/year-consumptions?year=2023&deviceId=2
    @GetMapping("/year-consumptions")
    public ResponseEntity<?> getTotalConsumptionForYear(
            @RequestParam Integer year,
            @RequestParam Long deviceId) {
        try {
            Long totalConsumption = recordService.getTotalConsumptionForYear(year, deviceId);
            return ResponseEntity.ok(totalConsumption);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // http://localhost:8080/api/records/month-consumptions?year=2023&deviceId=2&months=1,2,3
    @GetMapping("/month-consumptions")
    public ResponseEntity<?> getMeasurementsForMonths(
            @RequestParam Integer year,
            @RequestParam Long deviceId,
            @RequestParam List<Integer> months) {
        try {
            Map<Integer, Long> measurementsForMonths = recordService.getMeasurementsForMonths(year, deviceId, months);
            return ResponseEntity.ok(measurementsForMonths);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

