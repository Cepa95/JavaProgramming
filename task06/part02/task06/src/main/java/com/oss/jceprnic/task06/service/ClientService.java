package com.oss.jceprnic.task06.service;

import com.oss.jceprnic.task06.model.Client;
import com.oss.jceprnic.task06.repository.ClientRepository;
import lombok.AllArgsConstructor;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class ClientService {


    private ClientRepository clientRepository;

    public void registerClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new RuntimeException("Duplicate email");
        }
        clientRepository.save(client);
    }

    public Optional<Client> loginClient(String email) {
        return clientRepository.findByEmail(email);
    }
}
