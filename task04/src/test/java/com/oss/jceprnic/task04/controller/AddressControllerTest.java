package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Address;
import com.oss.jceprnic.task04.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Test
    void getAddressByCity() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String city = "Split";
        Address expectedAddress = new Address();
        when(addressService.findByCity(city)).thenReturn(expectedAddress);

        // Act
        Address actualAddress = addressController.getAddressByCity(city);

        // Assert
        assertEquals(expectedAddress, actualAddress);

        // Verify that the findByCity method was called
        verify(addressService, times(1)).findByCity(city);
    }

    @Test
    void createAddress_Success() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Address savedAddress = new Address(); // Create an Address object with required data
        when(addressService.saveAddress()).thenReturn(savedAddress);

        // Act
        ResponseEntity<?> responseEntity = addressController.createAddress();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedAddress, responseEntity.getBody());

        // Verify that the saveAddress method was called
        verify(addressService, times(1)).saveAddress();
    }

    @Test
    void createAddress_Exception() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(addressService.saveAddress()).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity<?> responseEntity = addressController.createAddress();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", responseEntity.getBody());

        // Verify that the saveAddress method was called
        verify(addressService, times(1)).saveAddress();
    }
}
