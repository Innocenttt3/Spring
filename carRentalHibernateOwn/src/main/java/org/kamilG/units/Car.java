package org.kamilG.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

    public Car(int id, String brand, String model, int year, int price) {
        super(id, brand, model, year, price);
    }

    public Car() {

    }

}
