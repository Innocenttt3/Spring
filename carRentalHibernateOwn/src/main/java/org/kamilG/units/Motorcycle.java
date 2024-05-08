package org.kamilG.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {
    private String category;

    public Motorcycle(int id, String brand, String model, int year, int price,boolean isRented, String category) {
        super(id, brand, model, year, price, isRented);
        this.category = category;
    }

    public Motorcycle() {

    }
    @Override
    public String toString() {
        return "Motorcycle - " + super.toString() + ", category: " + category;
    }
}
