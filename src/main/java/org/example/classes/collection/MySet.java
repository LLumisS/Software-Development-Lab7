package org.example.classes.collection;

import org.example.classes.error.ExceptionHandler;
import org.example.classes.flowers.Flower;

import java.util.*;

/**
 *  element of linked list
 *  flower + next element
 */
class Node {
    Flower data;
    Node next;

    Node(Flower data) {
        this.data = data;
        this.next = null;
    }
}

public class MySet implements Set<Flower> {
    private Node head;
    private int size;

    /**
     *  custom iterator implementation for MySet class
     */
    private static class MySetIterator implements Iterator<Flower> {

        private Node current;

        public MySetIterator(Node head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Flower next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Flower data = current.data;
            current = current.next;
            return data;
        }
    }

    /**
     *  default constructor
     *  creates an instance with 0 size and no elements
     */
    public MySet() {
        this.head = null;
        this.size = 0;
    }

    /**
     *  constructor with one element
     *  creates an instance with first element
     */
    public MySet(Flower head) {
        this.head = new Node(head);
        this.size = 1;
    }

    /**
     *  constructor with standard collection of flowers
     *  creates an instance with given elements
     */
    public MySet(Collection<? extends Flower> collection) {
        this.addAll(collection);
    }

    /**
     *  constructor with array of flowers
     *  creates an instance with given elements
     */
    public MySet(Flower[] array) {
        this.addAll(List.of(array));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  check if the given element is in the MySet
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Flower flower)) {
            return false;
        }

        Node current = head;
        while (current != null) {
            if (flower.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     *  return a new iterator for this instance
     */
    @Override
    public Iterator<Flower> iterator() {
        return new MySetIterator(head);
    }

    /**
     *  return a new array of MySet elements
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        Node current = head;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }

        return array;
    }

    /**
     *  add given element
     */
    @Override
    public boolean add(Flower flower) {
        if (!contains(flower)) {
            Node node = new Node(flower);
            node.next = head;
            head = node;
            size++;
            return true;
        }
        return false;
    }

    /**
     *  remove given element
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        Node current = head;
        Node prev = null;

        while (current != null) {
            if (o.equals(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    /**
     *  add all given elements
     */
    @Override
    public boolean addAll(Collection<? extends Flower> c) {
        boolean modified = false;
        for (Flower element : c) {
            modified |= add(element);
        }
        return modified;
    }

    /**
     *  clear the collection
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     *  remove all given elements
     */
    @Override
    public boolean removeAll(Collection c) {
        boolean modified = false;
        for (Object element : c) {
            modified |= remove(element);
        }
        return modified;
    }

    /**
     *  retain in the MySet only given elements
     */
    @Override
    public boolean retainAll(Collection c) {
        boolean modified = false;
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (!c.contains(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                modified = true;
            } else {
                prev = current;
            }
            current = current.next;
        }

        return modified;
    }

    /**
     *  check if the given collection elements is in the MySet
     */
    @Override
    public boolean containsAll(Collection c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     *  write the list elements in given array
     */
    @Override
    public Flower[] toArray(Object[] a) {
        if (!(a instanceof Flower[])) {
            throw new ExceptionHandler("Invalid array type given");
        }

        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        int index = 0;
        Node current = head;
        while (current != null) {
            a[index++] = current.data;
            current = current.next;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return (Flower[]) a;
    }

    /**
     *  sort of flowers by its freshness
     */
    public void sortByFreshness() {
        Flower[] array = toArray(new Flower[size]);

        Arrays.sort(array, Comparator.comparingInt(Flower::getFreshness));

        head = null;
        size = 0;

        this.addAll(Arrays.asList(array));
    }
}
