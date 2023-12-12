package org.example.classes;

import org.example.classes.flowers.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BouquetTest {
    private Bouquet bouquet;

    @Before
    public void setUp() {
        // Given
        Rose rose = new Rose(7, 30, 1);
        Iris iris = new Iris(5, 25, 2);
        Tulip tulip = new Tulip(10, 35, 3);
        Flower[] flowers = new Flower[]{ rose, iris, tulip };

        Accessory accessory = new Accessory("Ribbon", 4);

        bouquet = new Bouquet(flowers, accessory);
    }

    @Test
    public void testGetPrice() {
        // Then
        assertEquals(10, bouquet.getPrice(), 0.01);
    }

    @Test
    public void testGetFlowersByLength() {
        // Then
        String expected = """
                Iris\t5\t25.0\t2.0
                Rose\t7\t30.0\t1.0
                """;
        assertEquals(expected, bouquet.getFlowersByLength(25, 30));
    }

    @Test
    public void testGetBouquet() {
        String expected = """
                Tulip\t10\t35.0\t3.0
                Iris\t5\t25.0\t2.0
                Rose\t7\t30.0\t1.0
                Ribbon\t\t\t\t4.0
                \tTotal price: 10.0""";
        assertEquals(expected, bouquet.getBouquet());
    }
}
