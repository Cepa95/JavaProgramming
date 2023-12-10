package com.oss.jceprnic.task05.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.jceprnic.task05.model.Address;
import com.oss.jceprnic.task05.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    public Address findByCity(String city) {
        return addressRepository.findByCityIgnoreCase(city)
                .orElseThrow(() -> new NoSuchElementException("Addresses not found with city: " + city));
    }
    public Address saveAddress() {
        try {
            File file = new File("src/main/resources/address.json");
            Address address = objectMapper.readValue(file, Address.class);
            return addressRepository.save(address);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading address data from address.json and saving to the database.", e);
        }
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException("Address not found with id: " + addressId));
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long addressId, Address updatedAddress) {
        Address existingAddress = getAddressById(addressId);

        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setCountry(updatedAddress.getCountry());
        existingAddress.setZipCode(updatedAddress.getZipCode());


        return addressRepository.save(existingAddress);
    }


    public void deleteAddress(Long addressId) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException("Address not found with id: " + addressId));

        addressRepository.delete(existingAddress);
    }



}
