package org.example.vehicles;

public abstract class Vehicle {
    private final String brand;
    private final String model;
    private final int year;
    private final int price;
    private boolean rented;
    private final int id;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRented() {
        return rented;
    }

    public int getId() {
        return id;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Vehicle(String brand, String model, int year, int price, boolean rented, int id) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
        this.id = id;
    }

    public String toDb() {
        return brand + "," + model + "," + year + "," + price + "," + rented + "," + id;
    }

    public String toString() {
        return "brand: " + brand + ", model: " + model + ", year: " + year + ", price: " + price + ", is rented: " + rented + ", id: " + id;
    }

}
