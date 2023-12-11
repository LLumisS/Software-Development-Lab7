package org.example.classes.flowers;

public class Rose extends Flower {
    public Rose(int freshness, double length) {
        super(freshness, length, 15.99);
    }

    public Rose(int freshness, double length, double price) {
        super(freshness, length, price);
    }

    @Override
    public String getName() {
        return "Rose";
    }
}
