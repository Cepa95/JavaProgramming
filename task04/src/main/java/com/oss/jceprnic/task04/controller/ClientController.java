package com.oss.jceprnic.task04.controller;

import com.oss.jceprnic.task04.model.Client;
import com.oss.jceprnic.task04.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/get")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    //@PostMapping("/save")
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
}
