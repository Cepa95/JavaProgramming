package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Client;
import com.oss.jceprnic.task04.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getClientByEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String email = "mail@gmail.com";
        Client expectedClient = new Client();
        when(clientService.findByEmail(email)).thenReturn(expectedClient);

        // Act
        Client actualClient = clientController.getClientByEmail(email);

        // Assert
        assertEquals(expectedClient, actualClient);

        // Verify that the findByEmail method was called
        verify(clientService, times(1)).findByEmail(email);
    }

    @Test
    void getClientByAddressId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Long addressId = 1L;
        Client expectedClient = new Client();
        when(clientService.findByAddressId(addressId)).thenReturn(expectedClient);

        // Act
        Client actualClient = clientController.getClientByAddressId(addressId);

        // Assert
        assertEquals(expectedClient, actualClient);

        // Verify that the findByAddressId method was called
        verify(clientService, times(1)).findByAddressId(addressId);
    }

    @Test
    void createClient() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Client savedClient = new Client();
        when(clientService.saveClient()).thenReturn(savedClient);

        // Act
        ResponseEntity<?> responseEntity = clientController.createClient();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedClient, responseEntity.getBody());

        // Verify that the saveClient method was called
        verify(clientService, times(1)).saveClient();
    }

    @Test
    void createClient_Exception() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(clientService.saveClient()).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity<?> responseEntity = clientController.createClient();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody());

        // Verify that the saveClient method was called
        verify(clientService, times(1)).saveClient();
    }
}
