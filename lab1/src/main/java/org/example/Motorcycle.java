package org.example;

public class Motorcycle extends Vehicle {
    private String category;


    public Motorcycle(String brand, String model, int year, int price, boolean rented, int id, String Category) {
        super(brand, model, year, price, rented, id);
        this.category = Category;
    }
}
