package com.oss.jceprnic.task06.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.jceprnic.task06.model.Address;
import com.oss.jceprnic.task06.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    private ObjectMapper objectMapper;
    private AddressService addressService;

    @Test
    void findByCityValidCity() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        addressService = new AddressService(addressRepository, objectMapper);

        String city = "TestCity";
        Address expectedAddress = new Address();

        when(addressRepository.findByCityIgnoreCase(city)).thenReturn(Optional.of(expectedAddress));

        // Act
        Address foundAddress = addressService.findByCity(city);

        // Assert
        assertNotNull(foundAddress);
        assertEquals(expectedAddress, foundAddress);

        // Verify that the findByCity method was called
        verify(addressRepository, times(1)).findByCityIgnoreCase(city);
    }

    @Test
    void findByCity() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        addressService = new AddressService(addressRepository, objectMapper);

        String city = "NonExistentCity";

        when(addressRepository.findByCityIgnoreCase(city)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> addressService.findByCity(city));

        // Verify that the findByCity method was called
        verify(addressRepository, times(1)).findByCityIgnoreCase(city);
    }

    @Test
    void saveAddress() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        addressService = new AddressService(addressRepository, objectMapper);

        File file = new File("src/test/resources/addressTest.json"); // Create a JSON file with valid address data
        Address expectedAddress = objectMapper.readValue(file, Address.class);

        when(addressRepository.save(any())).thenReturn(expectedAddress);

        // Act
        Address savedAddress = addressService.saveAddress();

        // Assert
        assertNotNull(savedAddress);
        assertEquals(expectedAddress, savedAddress);

        // Verify that the save method was called
        verify(addressRepository, times(1)).save(any());
    }
}
