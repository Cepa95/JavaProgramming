package org.oss.jceprnic;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //String json = "{\"name\":\"water temperature\",\"lowerRange\":-3266.8,\"higherRange\":3266.8,\"factor\":10, \"unit\":\"C\"}";
        Device device = new Device("./src/main/resources/package.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            device = objectMapper.readValue(new File(device.getJsonPath()), Device.class);
        } catch (IOException e) {
            System.err.println("JSON error: " + e.getMessage());

        }
        device.runDevice();
        System.out.println("...done publishing messages");
    }
}

