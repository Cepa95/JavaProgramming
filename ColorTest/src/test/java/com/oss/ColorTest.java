package com.oss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class ColorTest {
    private Color color;

    @BeforeEach
    void setUp() {
        color = new Color();
    }

    @Test
    void getFirstName() {
        color.setFirstName("Josip");
        assertEquals("Josip", color.getFirstName());
    }

    @Test
    void getFullName() {
        color.setFirstName("Josip");
        color.setLastName("Ceprnic");
       // assertEquals("Josip Ceprnic", color.getFullName());
        assertEquals("JOSIP CEPRNIC", color.getFullName());

    }

    @Test
    void getLastName() {
        color.setLastName("Ceprnic");
        assertEquals("Ceprnic", color.getLastName());
    }
}