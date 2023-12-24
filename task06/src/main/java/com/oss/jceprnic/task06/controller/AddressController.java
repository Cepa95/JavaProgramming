package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Address;
import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    //http://localhost:8080/api/addresses/cities?city=Zagreb
    @GetMapping("/cities")
    public Address getAddressByCity(@RequestParam String city) {
        return addressService.findByCity(city);
    }

    //http://localhost:8080/api/addresses
    @PostMapping("/create")
    public ResponseEntity<?> createAddress() {
        try {
            Address savedAddress = addressService.saveAddress();
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    //@GetMapping
    //public List<Address> getAllAddresses() {
    //    return addressService.getAllAddresses();
    //}

    // http://localhost:8080/api/addresses?page=0&size=5&sortBy=city&sortOrder=asc
    @GetMapping
    public ResponseEntity<Page<Address>> getAllAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "city") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {


        Page<Address> addressesPage = addressService.getAllRecords(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(addressesPage);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long addressId) {
        try {
            Address address = addressService.getAddressById(addressId);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address newAddress = addressService.addAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }


    // PUT => http://localhost:8080/api/addresses/1
    // omoguciti u headeru key => Content-Type  value => application/json
    // body,raw => {
    //  "street": "Updated Street",
    //  "city": "Updated City",
    //  "country": "Updated Country",
    //  "zipCode": 12345
    //}
    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @RequestBody Address updatedAddress) {
        try {
            Address updated = addressService.updateAddress(addressId, updatedAddress);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // DELETE => http://localhost:8080/api/addresses/4
    // provjeri status => otprilike desni donji kut
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        try {
            addressService.deleteAddress(addressId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
