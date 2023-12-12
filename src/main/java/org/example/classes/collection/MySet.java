package org.example.classes.collection;

import org.example.classes.error.ExceptionHandler;
import org.example.classes.flowers.Flower;

import java.util.*;

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

    public MySet() {
        this.head = null;
        this.size = 0;
    }

    public MySet(Flower head) {
        this.head = new Node(head);
        this.size = 1;
    }

    public MySet(Collection<? extends Flower> collection) {
        this.addAll(collection);
    }

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

    @Override
    public Iterator<Flower> iterator() {
        return new MySetIterator(head);
    }

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

    @Override
    public boolean addAll(Collection<? extends Flower> c) {
        boolean modified = false;
        for (Flower element : c) {
            modified |= add(element);
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean modified = false;
        for (Object element : c) {
            modified |= remove(element);
        }
        return modified;
    }

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

    @Override
    public boolean containsAll(Collection c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

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

    public void sortByFreshness() {
        Flower[] array = toArray(new Flower[size]);

        Arrays.sort(array, Comparator.comparingInt(Flower::getFreshness));

        head = null;
        size = 0;

        this.addAll(Arrays.asList(array));
    }
}
