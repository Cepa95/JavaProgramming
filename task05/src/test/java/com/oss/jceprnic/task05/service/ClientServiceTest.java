package com.oss.jceprnic.task05.service;
import com.oss.jceprnic.task05.model.Client;
import com.oss.jceprnic.task05.repository.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;


import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ClientService clientService;


    @Test
    void findByEmail() {
        // Arrange
        String testEmail = "test@example.com";
        Client testClient = new Client();
        when(clientRepository.findByEmail(testEmail)).thenReturn(Optional.of(testClient));

        // Act
        Client result = clientService.findByEmail(testEmail);

        // Assert
        assertNotNull(result);
        assertEquals(testClient, result);

        // Verify that the repository method was called with the correct argument
        verify(clientRepository, times(1)).findByEmail(testEmail);
    }

    @Test
    void findByAddressId() {
        // Arrange
        Long testAddressId = 123L;
        Client testClient = new Client();
        when(clientRepository.findByAddressId(testAddressId)).thenReturn(Optional.of(testClient));

        // Act
        Client result = clientService.findByAddressId(testAddressId);

        // Assert
        assertNotNull(result);
        assertEquals(testClient, result);

        // Verify that the repository method was called with the correct argument
        verify(clientRepository, times(1)).findByAddressId(testAddressId);
    }

    @Test
    void saveClient() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        clientService = new ClientService(clientRepository, objectMapper);

        File file = new File("src/test/resources/clientTest.json");
        Client expectedClient = objectMapper.readValue(file, Client.class);

        when(clientRepository.save(any())).thenReturn(expectedClient);

        // Act
        Client savedClient = clientService.saveClient();

        // Assert
        assertNotNull(savedClient);
        assertEquals(expectedClient, savedClient);

        // Verify that the save method was called
        verify(clientRepository, times(1)).save(any());
    }

}
