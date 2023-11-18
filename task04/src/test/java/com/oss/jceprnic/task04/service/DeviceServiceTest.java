package com.oss.jceprnic.task04.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.jceprnic.task04.model.Device;
import com.oss.jceprnic.task04.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    private ObjectMapper objectMapper;
    private DeviceService deviceService;

    @Test
    void findByNameValidName() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        deviceService = new DeviceService(deviceRepository, objectMapper);

        String deviceName = "TestDevice";
        Device expectedDevice = new Device();

        when(deviceRepository.findByName(deviceName)).thenReturn(Optional.of(expectedDevice));

        // Act
        Device foundDevice = deviceService.findByName(deviceName);

        // Assert
        assertNotNull(foundDevice);
        assertEquals(expectedDevice, foundDevice);

        // Verify that the findByName method was called
        verify(deviceRepository, times(1)).findByName(deviceName);
    }

    @Test
    void findByNameInvalidName() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        deviceService = new DeviceService(deviceRepository, objectMapper);

        String deviceName = "NonExistentDevice";

        when(deviceRepository.findByName(deviceName)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> deviceService.findByName(deviceName));

        // Verify that the findByName method was called
        verify(deviceRepository, times(1)).findByName(deviceName);
    }

    @Test
    void findByIdWithRecordsValidId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        deviceService = new DeviceService(deviceRepository, objectMapper);

        Long deviceId = 1L;
        Device expectedDevice = new Device();

        when(deviceRepository.findByIdWithRecords(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        Device foundDevice = deviceService.findByIdWithRecords(deviceId);

        // Assert
        assertNotNull(foundDevice);
        assertEquals(expectedDevice, foundDevice);

        // Verify that the findByIdWithRecords method was called
        verify(deviceRepository, times(1)).findByIdWithRecords(deviceId);
    }

    @Test
    void findByIdWithRecords() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        deviceService = new DeviceService(deviceRepository, objectMapper);

        Long deviceId = 999L;

        when(deviceRepository.findByIdWithRecords(deviceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> deviceService.findByIdWithRecords(deviceId));

        // Verify that the findByIdWithRecords method was called
        verify(deviceRepository, times(1)).findByIdWithRecords(deviceId);
    }

    @Test
    void saveDeviceValidDevice() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        deviceService = new DeviceService(deviceRepository, objectMapper);

        File file = new File("src/test/resources/deviceTest.json");
        Device expectedDevice = objectMapper.readValue(file, Device.class);

        when(deviceRepository.save(any())).thenReturn(expectedDevice);

        // Act
        Device savedDevice = deviceService.saveDevice();

        // Assert
        assertNotNull(savedDevice);
        assertEquals(expectedDevice, savedDevice);

        // Verify that the save method was called
        verify(deviceRepository, times(1)).save(any());
    }
}
