package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

    // http://localhost:8080/api/devices/1
    @PutMapping("{deviceId}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long deviceId, @RequestBody Device updatedDevice) {
        try {
            Device updated = deviceService.updateDevice(deviceId, updatedDevice);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/devices/1
    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long deviceId) {
        try {
            deviceService.deleteDevice(deviceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
