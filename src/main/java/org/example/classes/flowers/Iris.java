package org.example.classes.flowers;

public class Iris extends Flower {
    public Iris(int freshness, double length) {
        super(freshness, length, 16.99);
    }

    public Iris(int freshness, double length, double price) {
        super(freshness, length, price);
    }

    @Override
    public String getName() {
        return "Iris";
    }
}
