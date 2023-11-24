package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Address;
import com.oss.jceprnic.task04.model.Client;
import com.oss.jceprnic.task04.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    //http://localhost:8080/api/addresses?city=Split
    @GetMapping
    public Address getAddressByCity(@RequestParam String city) {
        return addressService.findByCity(city);
    }

    //http://localhost:8080/api/addresses
    @PostMapping
    public ResponseEntity<?> createAddress() {
        try {
            Address savedAddress = addressService.saveAddress();
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
