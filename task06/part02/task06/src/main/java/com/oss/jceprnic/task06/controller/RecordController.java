package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Client;
import com.oss.jceprnic.task06.model.Record;
import com.oss.jceprnic.task06.service.ClientService;
import com.oss.jceprnic.task06.service.RecordService;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;
    private final ClientService clientService;
    private final RestTemplate restTemplate;


    @GetMapping("/{clientId}/device/{deviceId}")
    public String showRecordsForDevice(@PathVariable Long clientId, @PathVariable Long deviceId, Model model) {
        List<Record> records = recordService.getAllRecordsForDevice(deviceId);

        Optional<Client> optionalClient = clientService.getClientById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            model.addAttribute("clientId", clientId);
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("clientName", client.getFirstName() + " " + client.getLastName());
            model.addAttribute("clientEmail", client.getEmail());
            model.addAttribute("records", records);
            return "records_data";
        }
        return "index";
    }



    @GetMapping("/forffffms")
    public String getRecordsByDeviceId(@PathVariable Long deviceId, Model model) {
        final String url = "http://localhost:8080/records/forms?deviceId=" + deviceId;

        ResponseEntity<List<Record>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Record>>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<Record> records = responseEntity.getBody();
            model.addAttribute("deviceId", deviceId);
            model.addAttribute("records", records);
            return "record_form";
        } else {
            return "error-page";
        }
    }

    @GetMapping("/forms")
    public String showRecordForm(@RequestParam Long deviceId, Model model) {
        model.addAttribute("deviceId", deviceId);
        model.addAttribute("record", new Record());

        return "record_form";
    }

    @PostMapping("/forms-web")
    public String submitRecordFormWeb(@ModelAttribute Record record, @RequestParam Long deviceId) {

        final String url = "http://localhost:8080/records/forms?deviceId=" + deviceId;

        //recordService.createRecord(record, deviceId);
        restTemplate.postForObject(url, record, Record.class);

        return "index";
    }

    @PostMapping("/forms")
    public String submitRecordForm(@ModelAttribute Record record, @RequestParam Long deviceId) {

        final String url = "http://localhost:8080/records/forms?deviceId=" + deviceId;

        recordService.createRecord(record, deviceId);

        return "index";
    }
}