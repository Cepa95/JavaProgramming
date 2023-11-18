package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Record;
import com.oss.jceprnic.task04.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
