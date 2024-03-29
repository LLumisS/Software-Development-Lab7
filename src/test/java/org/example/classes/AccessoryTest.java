package org.example.classes;

import org.example.classes.error.ExceptionHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccessoryTest {
    @Test
    public void testConstructor() {
        try {
            // When
            new Accessory("ribbon", -1);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Cannot set negative price", e.getMessage());
        }

        // When
        Accessory ribbon = new Accessory("ribbon", 10);
        // Then
        assertEquals("ribbon", ribbon.getName());
        assertEquals(10, ribbon.getPrice(), 0.01);
    }

    @Test
    public void testSetPrice() {
        // Given
        Accessory ribbon = new Accessory("ribbon", 10);

        // When
        ribbon.setPrice(15);

        // Then
        assertEquals(15, ribbon.getPrice(), 0.01);

        try {
            // When
            ribbon.setPrice(-1);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Cannot set negative price", e.getMessage());
        }
    }
}
