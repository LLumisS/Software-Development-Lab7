package org.example.classes.flowers;

import java.util.InputMismatchException;

public abstract class Flower {
    private int freshness;
    private double length;
    private double price;

    public Flower(int freshness, double length, double price) {
        if (length <= 0 || freshness < 0 || price < 0) {
            throw new InputMismatchException("Expected non-negative numbers");
        }

        this.freshness = freshness;
        this.length = length;
        this.price = price;
    }

    /**
     *  withers a flower if it's alive
     */
    public void wither() {
        if (freshness == 0) {
            throw new IllegalStateException("This flower is already withered...");
        }
        freshness--;
    }

    /**
     *  cuts a piece of flower of given length
     */
    public void cut(double length) {
        if (length < 0) {
            throw new InputMismatchException("Cannot cut negative length");
        }
        double result = this.length - length;
        if (result <= 0) {
            throw new IllegalStateException("Cannot cut this much");
        }

        this.length = result;
    }

    /**
     *  sets non-negative price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new InputMismatchException("Cannot set negative price");
        }

        this.price = price;
    }

    public abstract String getName();

    public int getFreshness() {
        return freshness;
    }

    public double getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }
}
