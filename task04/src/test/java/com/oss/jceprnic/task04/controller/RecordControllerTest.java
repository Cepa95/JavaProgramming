package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Record;
import com.oss.jceprnic.task04.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

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
}
