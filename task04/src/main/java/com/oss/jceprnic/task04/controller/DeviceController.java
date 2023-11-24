package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Device;
import com.oss.jceprnic.task04.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
@AllArgsConstructor
public class DeviceController {

    public final DeviceService deviceService;

    //http://localhost:8080/api/devices?name=smartDevice1
    @GetMapping
    public Device getDeviceByName(@RequestParam String name) {
        return deviceService.findByName(name);
    }

    // http://localhost:8080/api/devices/1
    @GetMapping("/{deviceId}")
    public Device getDeviceWithRecords(@PathVariable Long deviceId) {
        return deviceService.findByIdWithRecords(deviceId);
    }

    //http://localhost:8080/api/devices
    @PostMapping
    public ResponseEntity<?> createDevice() {
        try {
            Device savedDevice = deviceService.saveDevice();
            return ResponseEntity.ok(savedDevice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
