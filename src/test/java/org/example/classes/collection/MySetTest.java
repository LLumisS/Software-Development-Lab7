package org.example.classes.collection;

import org.example.classes.error.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.example.classes.flowers.*;
import java.util.*;

import static org.junit.Assert.*;

public class MySetTest {
    MySet defaultSet;
    MySet elementSet;
    MySet collectionSet;
    MySet arraySet;

    Flower flower = new Rose(10, 30);
    List<Flower> list = List.of(
            new Rose(10, 30),
            new Iris(6, 21));
    Flower[] array = new Flower[]{
            new Tulip(8, 25),
            new Rose(10, 30),
            new Iris(6, 21)
    };

    @Before
    public void setUp() {
        // Given
        defaultSet = new MySet();
        elementSet = new MySet(flower);
        collectionSet = new MySet(list);
        arraySet = new MySet(array);
    }

    @Test
    public void testSize() {
        // Then
        assertEquals(0, defaultSet.size());
        assertEquals(1, elementSet.size());
        assertEquals(2, collectionSet.size());
        assertEquals(3, arraySet.size());
    }

    @Test
    public void testIsEmpty() {
        // Then
        assertTrue(defaultSet.isEmpty());
        assertFalse(elementSet.isEmpty());
        assertFalse(collectionSet.isEmpty());
        assertFalse(arraySet.isEmpty());
    }

    @Test
    public void testContains() {
        // Then
        assertTrue(elementSet.contains(flower));
        assertFalse(elementSet.contains(new Iris(1, 1)));
    }

    @Test
    public void testIterator() {
        // When
        Iterator<Flower> iterator = arraySet.iterator();
        int[] expectedFreshness = new int[]{ 6, 10, 8 };

        // Then
        int index = 0;
        while(iterator.hasNext()) {
            Flower flower = iterator.next();
            int freshness = expectedFreshness[index++];
            assertEquals(freshness, flower.getFreshness());
        }
    }

    @Test
    public void testToArray() {
        // When
        Flower[] flowers = arraySet.toArray();

        // Then
        assertEquals(array.length, flowers.length);
        int length = flowers.length;
        for (int i = 0; i < length; i++) {
            assertEquals(array[i], flowers[length - i - 1]);
        }
    }

    @Test
    public void testToArrayWithInit() {
        // When
        Flower[] smallerSize = arraySet.toArray(new Flower[0]);
        Flower[] sameSize = arraySet.toArray(new Flower[3]);
        Flower[] biggerSize = arraySet.toArray(new Flower[]{
                null, null, null, null, new Rose(1, 1)
        });

        // Then
        assertEquals(array.length, smallerSize.length);
        assertEquals(array.length, sameSize.length);
        assertEquals(array.length, biggerSize.length);

        int length = array.length;
        for (int i = 0; i < length; i++) {
            assertEquals(array[i], smallerSize[length - i - 1]);
            assertEquals(array[i], sameSize[length - i - 1]);
            assertEquals(array[i], biggerSize[length - i - 1]);
        }
    }

    @Test
    public void testToArrayInvalidType() {
        try {
            // When
            arraySet.toArray(new String[array.length]);
            fail();
        } catch (ExceptionHandler e) {
            // Then
            assertEquals("Invalid array type given", e.getMessage());
        }
    }

    @Test
    public void testAdd() {
        // Then
        Flower flower = new Rose(1, 1);
        assertTrue(defaultSet.add(flower));
        assertFalse(defaultSet.add(flower));
        assertTrue(defaultSet.add(new Rose(1, 1)));
        assertEquals(2, defaultSet.size());
    }

    @Test
    public void testRemove() {
        // Then
        assertEquals(1, elementSet.size());
        assertTrue(elementSet.remove(flower));
        assertFalse(elementSet.contains(flower));
        assertFalse(elementSet.remove(flower));
        assertFalse(elementSet.remove(null));
        assertEquals(0, elementSet.size());
    }

    @Test
    public void testAddAll() {
        // Then
        assertTrue(elementSet.addAll(list));
        assertFalse(elementSet.addAll(list));
        assertEquals(3, elementSet.size());
    }

    @Test
    public void testClear() {
        // When
        arraySet.clear();

        // Then
        assertEquals(0, arraySet.size());
    }

    @Test
    public void testRemoveAll() {
        // Given
        Flower flower = new Rose(10, 30);
        assertTrue(arraySet.add(flower));
        assertTrue(arraySet.addAll(list));
        assertEquals(6, arraySet.size());

        // Then
        assertTrue(arraySet.removeAll(list));
        assertFalse(arraySet.removeAll(list));
        assertEquals(4, arraySet.size());
    }

    @Test
    public void testRetainAll() {
        // Given
        Flower flower = new Rose(10, 30);
        assertTrue(collectionSet.add(flower));
        assertEquals(3, collectionSet.size());

        // Then
        assertTrue(collectionSet.retainAll(list));
        assertFalse(collectionSet.retainAll(list));
        assertEquals(2, collectionSet.size());
    }

    @Test
    public void testContainsAll() {
        // Then
        assertTrue(collectionSet.containsAll(list));
        assertFalse(arraySet.containsAll(list));
    }

    @Test
    public void testSortByFreshness() {
        // When
        arraySet.sortByFreshness();
        int[] expectedFreshness = new int[]{ 10, 8, 6 };

        // Then
        int index = 0;
        for (Flower flower : arraySet) {
            assertEquals(expectedFreshness[index++], flower.getFreshness());
        }
    }
}
