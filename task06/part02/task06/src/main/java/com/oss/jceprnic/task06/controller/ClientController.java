package com.oss.jceprnic.task06.controller;

import com.oss.jceprnic.task06.model.Client;
import com.oss.jceprnic.task06.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {


    private ClientService clientService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Client client, Model model) {
        try {
            clientService.registerClient(client);
            return "index";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("client", client);
            return "registration";
        }
    }

    @GetMapping("/client-page")
    public String getClientPage(){
        return "client_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new Client());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Client client) {
        Optional<Client> optionalClient = clientService.loginClient(client.getEmail());

        if (optionalClient.isPresent()) {
            Long clientId = optionalClient.get().getId();
            Long deviceId = optionalClient.get().getDevice().getId();
            return "redirect:/records/" + clientId + "/device/" + deviceId;
        } else {
            return "redirect:/clients/login?error";
        }
    }


}
