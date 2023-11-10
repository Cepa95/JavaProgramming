package org.oss.jceprnic;

import org.eclipse.paho.client.mqttv3.MqttException;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeviceTest {
    private Device device;

    @BeforeEach
    public void setUp() {

        device = new Device("./src/main/resources/deserialization-test.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            device = objectMapper.readValue(new File(device.getJsonPath()), Device.class);
        } catch (IOException e) {
            System.err.println("JSON error: " + e.getMessage());
        }
        assertNotNull(device);
        assertNotNull(device.getSensors());
        assertNotNull(device.getAddress());
        assertNotNull(device.getTopic());
    }

    @Test
    public void testAddSensor() {
        assertEquals(2, device.getSensors().size());
    }

    @Test
    public void testRunDevice() throws MqttException {
        device.runDevice();
    }
}
