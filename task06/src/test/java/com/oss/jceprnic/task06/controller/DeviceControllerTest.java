package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @Test
    void getDeviceByName() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String deviceName = "smartDevice1";
        Device expectedDevice = new Device();
        when(deviceService.findByName(deviceName)).thenReturn(expectedDevice);

        // Act
        Device actualDevice = deviceController.getDeviceByName(deviceName);

        // Assert
        assertEquals(expectedDevice, actualDevice);

        // Verify that the findByName method was called
        verify(deviceService, times(1)).findByName(deviceName);
    }

    @Test
    void getDeviceWithRecords() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Long deviceId = 1L;
        Device expectedDevice = new Device();
        when(deviceService.findByIdWithRecords(deviceId)).thenReturn(expectedDevice);

        // Act
        Device actualDevice = deviceController.getDeviceWithRecords(deviceId);

        // Assert
        assertEquals(expectedDevice, actualDevice);

        // Verify that the findByIdWithRecords method was called
        verify(deviceService, times(1)).findByIdWithRecords(deviceId);
    }

    @Test
    void createDeviceSuccess() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Device savedDevice = new Device(); // Create a Device object with required data
        when(deviceService.saveDevice()).thenReturn(savedDevice);

        // Act
        ResponseEntity<?> responseEntity = deviceController.createDevice();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedDevice, responseEntity.getBody());

        // Verify that the saveDevice method was called
        verify(deviceService, times(1)).saveDevice();
    }

    @Test
    void createDeviceException() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(deviceService.saveDevice()).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity<?> responseEntity = deviceController.createDevice();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody());

        // Verify that the saveDevice method was called
        verify(deviceService, times(1)).saveDevice();
    }
}
