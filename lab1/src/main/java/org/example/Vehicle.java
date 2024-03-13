package org.example;

public abstract class Vehicle {
    private final String brand;
    private final String model;
    private final int year;
    private int price;
    private boolean rented;
    private final int id;

    public Vehicle(String brand, String model, int year, int price, boolean rented, int id) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
        this.id = id;
    }

    public String toCSV() {
        return brand + ";" + model + ";" + year + ";" + price + ";" + rented + ";" + id;
    }

    public String toString() {
        return "brand: " + brand + ", model: " + model + ", year: " + year + ", price: " + price + ", is rented: " + rented + ", id: " + id;
    }

}
