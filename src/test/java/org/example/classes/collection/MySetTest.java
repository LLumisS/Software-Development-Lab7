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
        // When
        defaultSet = new MySet();
        elementSet = new MySet(new Rose(10, 30));
        collectionSet = new MySet(list);
        arraySet = new MySet(array);
    }

    @Test
    public void testSize() {
        // When set up
        // Then
        assertEquals(0, defaultSet.size());
        assertEquals(1, elementSet.size());
        assertEquals(2, collectionSet.size());
        assertEquals(3, arraySet.size());
    }

    @Test
    public void testIsEmpty() {
        // When set up
        // Then
        assertTrue(defaultSet.isEmpty());
        assertFalse(elementSet.isEmpty());
        assertFalse(collectionSet.isEmpty());
        assertFalse(arraySet.isEmpty());
    }

    @Test
    public void testContains() {
        // When
        Flower flower = new Rose(10, 30);
        MySet set = new MySet(flower);

        // Then
        assertTrue(set.contains(flower));
        assertFalse(set.contains(new Iris(1, 1)));
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
        // When set up
        // Then
        Flower flower = new Rose(1, 1);
        assertTrue(defaultSet.add(flower));
        assertFalse(defaultSet.add(flower));
        assertTrue(defaultSet.add(new Rose(1, 1)));
        assertEquals(2, defaultSet.size());
    }

    @Test
    public void testRemove() {
        // When
        Flower flower = new Rose(10, 30);
        MySet set = new MySet(flower);

        // Then
        assertEquals(1, set.size());
        assertTrue(set.remove(flower));
        assertFalse(set.contains(flower));
        assertFalse(set.remove(flower));
        assertFalse(set.remove(null));
        assertEquals(0, set.size());
    }

    @Test
    public void testAddAll() {
        // When set up
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
        // When set up
        // Then
        Flower flower = new Rose(10, 30);
        assertTrue(arraySet.add(flower));
        assertTrue(arraySet.addAll(list));
        assertEquals(6, arraySet.size());

        assertTrue(arraySet.removeAll(list));
        assertFalse(arraySet.removeAll(list));
        assertEquals(4, arraySet.size());
    }

    @Test
    public void testRetainAll() {
        // When set up
        // Then
        Flower flower = new Rose(10, 30);
        assertTrue(collectionSet.add(flower));
        assertEquals(3, collectionSet.size());

        assertTrue(collectionSet.retainAll(list));
        assertFalse(collectionSet.retainAll(list));
        assertEquals(2, collectionSet.size());
    }

    @Test
    public void testContainsAll() {
        // When set up
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
