package com.oss.jceprnic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SensorTest {
    private Sensor sensor;

    @Before
    public void setUp() {
        sensor = new Sensor("Test Sensor", 0, 50, 1, "unit");
    }

    @Test
    public void testGetSensorData() {
        String expected = "Device: Test Sensor value: " + sensor.getValue() + " factor: " + sensor.getFactor() + " unit: " + sensor.getUnit();
        String actual = sensor.getSensorData();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValueWithinRange() {
        double lowerRange = sensor.getLowerRange();
        double higherRange = sensor.getHigherRange();
        double value = sensor.getValue();

        assertTrue(value >= lowerRange && value <= higherRange);
    }
    @Test
    public void testHigherLower() {
        double lowerRange = sensor.getLowerRange();
        double higherRange = sensor.getHigherRange();
        double value = sensor.getValue();

        assertTrue(higherRange > lowerRange);
    }

}
