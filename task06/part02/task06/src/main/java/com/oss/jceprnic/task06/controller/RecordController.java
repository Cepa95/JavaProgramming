package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Client;
import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.service.ClientService;
import com.oss.jceprnic.task06.service.DeviceService;
import com.oss.jceprnic.task06.service.RecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;
    private final ClientService clientService;
    private final DeviceService deviceService;

    @GetMapping("/{clientId}/device/{deviceId}")
    public String showRecordsForDevice(@PathVariable Long clientId, @PathVariable Long deviceId, Model model) {
        List<Record> records = recordService.getAllRecordsForDevice(deviceId);

        Optional<Client> optionalClient = clientService.getClientById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            // Add client information to the model
            model.addAttribute("clientId", clientId);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("clientName", client.getFirstName() + " " + client.getLastName());
            model.addAttribute("clientEmail", client.getEmail());
            model.addAttribute("records", records);

            return "records_data";
        }
        return "login";
    }

}
