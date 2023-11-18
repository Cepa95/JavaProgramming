package com.oss.jceprnic.task04.service;

import com.oss.jceprnic.task04.model.Device;
import com.oss.jceprnic.task04.model.Record;
import com.oss.jceprnic.task04.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordService recordService;

    @Test
    void getAllRecords() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        List<Record> expectedRecords = new ArrayList<>(); // Add some sample records
        when(recordRepository.findAll()).thenReturn(expectedRecords);

        // Act
        List<Record> actualRecords = recordService.getAllRecords();

        // Assert
        assertEquals(expectedRecords, actualRecords);

        // Verify that the findAll method was called
        verify(recordRepository, times(1)).findAll();
    }

    @Test
    void addRecordInvalidDeviceId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Long invalidDeviceId = 999L;
        when(recordRepository.findDeviceById(invalidDeviceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> recordService.addRecord(invalidDeviceId));

        // Verify that the findDeviceById method was called
        verify(recordRepository, times(1)).findDeviceById(invalidDeviceId);
        // Ensure that save method is not called when there is an invalid device id
        verify(recordRepository, never()).save(any(Record.class));
    }
}
