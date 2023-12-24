package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecordControllerTest {

    private RecordService recordService;
    private RecordController recordController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        recordService = mock(RecordService.class);
        recordController = new RecordController(recordService);
        mockMvc = MockMvcBuilders.standaloneSetup(recordController).build();
    }

    @Test
    void getAllRecords() throws Exception {
        when(recordService.getAllRecords()).thenReturn((List<Record>) mockRecords());

        mockMvc.perform(get("/api/records"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void addRecord() throws Exception {
        when(recordService.addRecord(3L)).thenReturn(new Record());

        mockMvc.perform(post("/api/records/add")
                        .param("deviceId", "3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private Iterable<Record> mockRecords() {
        return Arrays.asList(new Record(), new Record());
    }


    @Test
    public void testGetTotalConsumptionForYearSuccess() {
        // Arrange
        int year = 2022;
        long deviceId = 1L;
        long expectedConsumption = 500L;

        when(recordService.getTotalConsumptionForYear(year, deviceId)).thenReturn(expectedConsumption);

        // Act
        ResponseEntity<?> response = recordController.getTotalConsumptionForYear(year, deviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedConsumption, response.getBody());
        verify(recordService, times(1)).getTotalConsumptionForYear(year, deviceId);
    }

    @Test
    public void testGetTotalConsumptionForYearNotFound() {
        // Arrange
        int year = 2022;
        long deviceId = 1L;

        when(recordService.getTotalConsumptionForYear(year, deviceId)).thenThrow(new NoSuchElementException("Records not found."));

        // Act
        ResponseEntity<?> response = recordController.getTotalConsumptionForYear(year, deviceId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Records not found.", response.getBody());
        verify(recordService, times(1)).getTotalConsumptionForYear(year, deviceId);
    }


    @Test
    public void testGetMeasurementsForMonthsSuccess() {
        // Arrange
        int year = 2022;
        long deviceId = 1L;
        List<Integer> months = Arrays.asList(1, 2, 3);
        Map<Integer, Long> expectedMeasurements = new HashMap<>();
        expectedMeasurements.put(1, 100L);
        expectedMeasurements.put(2, 120L);
        expectedMeasurements.put(3, 99L);

        when(recordService.getMeasurementsForMonths(year, deviceId, months)).thenReturn(expectedMeasurements);

        // Act
        ResponseEntity<?> response = recordController.getMeasurementsForMonths(year, deviceId, months);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMeasurements, response.getBody());
        verify(recordService, times(1)).getMeasurementsForMonths(year, deviceId, months);
    }

    @Test
    public void testGetMeasurementsForMonthsBadRequest() {
        // Arrange
        int year = 2022;
        long deviceId = 1L;
        List<Integer> months = Arrays.asList(1, 2, 3);

        when(recordService.getMeasurementsForMonths(year, deviceId, months)).thenThrow(new NoSuchElementException("Records not found."));

        // Act
        ResponseEntity<?> response = recordController.getMeasurementsForMonths(year, deviceId, months);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Records not found.", response.getBody());
        verify(recordService, times(1)).getMeasurementsForMonths(year, deviceId, months);
    }

}
