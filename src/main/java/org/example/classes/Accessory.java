package org.example.classes;

import org.example.classes.error.ExceptionHandler;

import java.util.InputMismatchException;

public class Accessory {
    private final String name;
    private double price;

    public Accessory (String name, double price) {
        if (price < 0) {
            throw new ExceptionHandler("Cannot set negative price");
        }

        this.name = name;
        this.price = price;
    }

    /**
     *  sets non-negative price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new ExceptionHandler("Cannot set negative price");
        }

        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
