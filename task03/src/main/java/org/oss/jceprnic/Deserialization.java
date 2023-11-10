package org.oss.jceprnic;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Deserialization {
    public static Device deserialize(String path) {
        Device device = new Device(path);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(device.getJsonPath()), Device.class);
        } catch (IOException e) {
            System.err.println("JSON error: " + e.getMessage());
        }
        System.out.println("...done publishing messages");
        return null;
    }
}