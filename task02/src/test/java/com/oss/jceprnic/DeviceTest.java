package com.oss.jceprnic;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class DeviceTest {
    private Device device;


    @Before
    public void setUp() {
        device = new Device("TestDevice", "tcp://localhost:1883");
        Sensor sensor1 = new Sensor("Sensor1", 0, 100, 1, "unit1");
        Sensor sensor2 = new Sensor("Sensor2", 0, 100, 2, "unit2");
        device.addSensor(sensor1);
        device.addSensor(sensor2);
    }

    @Test
    public void testAddSensor() {
        assertEquals(2, device.getSensors().size());
    }

    @Test
    public void testRunDevice() {
        try {
            device.runDevice();
        } catch (MqttException e) {
            Assertions.fail("runDevice method should not throw an exception");
        }
    }

}