package org.example.classes.flowers;

public class Tulip extends Flower{
    public Tulip(int freshness, double length) {
        super(freshness, length, 17.49);
    }

    public Tulip(int freshness, double length, double price) {
        super(freshness, length, price);
    }

    @Override
    public String getName() {
        return "Tulip";
    }
}
