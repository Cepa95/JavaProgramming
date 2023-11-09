package org.oss.jceprnic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        // Initialize a Sensor object before each test
        sensor = new Sensor("TestSensor", 0, 100, 0, 1, "C");
    }

    @Test
    void testGetRandomValue() {
        sensor.getRandomValue();
        assertTrue(sensor.getValue() >= sensor.getLowerRange() && sensor.getValue() <= sensor.getHigherRange());
    }


    @Test
    void testInitialValue() {
        assertEquals(0, sensor.getValue());
    }

    @Test
    void testSetGetValue() {
        sensor.setValue(50);
        assertEquals(50, sensor.getValue());
    }
}
