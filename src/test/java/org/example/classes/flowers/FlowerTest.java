package org.example.classes.flowers;

import org.example.classes.error.ExceptionHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlowerTest {
    @Test
    public void testConstructor() {
        try {
            // When
            new Rose(-1, 10, 10);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Expected non-negative numbers", e.getMessage());
        }

        try {
            // When
            new Iris(10, -1, 10);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Expected non-negative numbers", e.getMessage());
        }

        try {
            // When
            new Tulip(10, 10, -1);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Expected non-negative numbers", e.getMessage());
        }

        // When
        Rose rose = new Rose(8, 9, 10);
        // Then
        assertEquals(8, rose.getFreshness());
        assertEquals(9, rose.getLength(), 0.01);
        assertEquals(10, rose.getPrice(), 0.01);
    }

    @Test
    public void testWither() {
        // Given
        Rose rose = new Rose(3, 10);

        // When
        rose.wither();
        rose.wither();

        // Then
        assertEquals(1, rose.getFreshness());

        try {
            // When
            rose.wither();
            rose.wither();
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("This flower is already withered...", e.getMessage());
        }
    }

    @Test
    public void testCut() {
        // Given
        Rose rose = new Rose(3, 10);

        // When
        rose.cut(5);

        // Then
        assertEquals(5, rose.getLength(), 0.01);

        try {
            // When
            rose.cut(10);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Cannot cut this much", e.getMessage());
        }

        try {
            // When
            rose.cut(-1);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Cannot cut negative length", e.getMessage());
        }
    }

    @Test
    public void testSetPrice() {
        // Given
        Rose rose = new Rose(1, 1);

        // When
        rose.setPrice(15);

        // Then
        assertEquals(15, rose.getPrice(), 0.01);

        try {
            // When
            rose.setPrice(-1);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Cannot set negative price", e.getMessage());
        }
    }
}
