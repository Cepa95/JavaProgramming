package com.oss.jceprnic.task05.controller;

import com.oss.jceprnic.task05.model.Client;
import com.oss.jceprnic.task05.service.ClientService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    //http://localhost:8080/api/clients/by-email?email=mail@gmail.com
    @GetMapping("/by-email")
    public Client getClientByEmail(@RequestParam String email) {
        return clientService.findByEmail(email);
    }

    //http://localhost:8080/api/clients/by-address?id=1
    @GetMapping("/by-address")
    public Client getClientByAddressId(@RequestParam Long id) {
        return clientService.findByAddressId(id);
    }

    //@GetMapping("/get")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    //@PostMapping("/save")
    // POSTMAN => body key:jsonFilePath, value: classpath:client.json
    @PostMapping
    public ResponseEntity<?> createClient() {
        try {
            Client savedClient = clientService.saveClient();
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    // PUT => http://localhost:8080/api/clients/2
    // omoguciti u headeru key => Content-Type  value => application/json
    // body,raw => {{
    //  "firstName": "UpdatedFirstName",
    //  "lastName": "UpdatedLastName",
    //  "email": "updated.email@example.com"
    //
    //}
    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client updatedClient) {
        try {
            Client updated = clientService.updateClient(clientId, updatedClient);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/clients/1
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        try {
            clientService.deleteClient(clientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
