package org.example;

public class Motorcycle extends Vehicle {
    private final String category;


    public Motorcycle(String brand, String model, int year, int price, boolean rented, int id, String Category) {
        super(brand, model, year, price, rented, id);
        this.category = Category;
    }

    @Override
    public String toString() {
        return super.toString() + ", category: " + category;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + category;
    }
}
