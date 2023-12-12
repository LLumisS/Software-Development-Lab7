package org.example.classes;

import org.example.classes.collection.MySet;
import org.example.classes.flowers.Flower;

public class Bouquet {
    private final MySet flowers;
    private final Accessory accessory;

    public Bouquet(Flower[] flowers, Accessory accessories) {
        this.flowers = new MySet(flowers);
        this.accessory = accessories;
    }

    public Bouquet(MySet flowers, Accessory accessories) {
        this.flowers = flowers;
        this.accessory = accessories;
    }

    /**
     *  calculates a price of a bouquet
     *  depends on flowers/accessories price
     */
    public double getPrice() {
        double price = 0;

        for (Flower flower : flowers) {
            price += flower.getPrice();
        }
        price += accessory.getPrice();

        return price;
    }

    /**
     *  supporting method
     *  returns String of flower's info
     */
    private String getFlowerInfo(Flower flower) {
        return flower.getName() + "\t" +
                flower.getFreshness() + "\t" +
                flower.getLength() + "\t" +
                flower.getPrice() + "\n";
    }

    /**
     *  returns String of flowers with info
     *  which length fits the given range
     */
    public String getFlowersByLength(double from, double to) {
        StringBuilder result = new StringBuilder();

        for (Flower flower : flowers) {
            double length = flower.getLength();
            if (from <= length && length <= to) {
                result.append(getFlowerInfo(flower));
            }
        }

        return result.toString();
    }

    /**
     *  bubble sort of flowers
     */
    public void sortFlowersByFreshness() {
        flowers.sortByFreshness();
    }

    /**
     *  returns String of flowers and accessories with info
     */
    public String getBouquet() {
        StringBuilder bouquet = new StringBuilder();

        for (Flower flower : flowers) {
            bouquet.append(getFlowerInfo(flower));
        }
        String name = accessory.getName();
        double price = accessory.getPrice();
        bouquet.append(name).append("\t\t\t\t").append(price).append("\n");

        bouquet.append("\tTotal price: ").append(getPrice());

        return bouquet.toString();
    }
}
