package org.kamilG.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {
    private String category;

    public Motorcycle(int id, String brand, String model, int year, int price, String category) {
        super(id, brand, model, year, price);
        this.category = category;
    }

    public Motorcycle() {

    }
}
