package org.kamilG.demo.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

  public Car(int id, String brand, String model, int year, int price, boolean isRented) {
    super(id, brand, model, year, price, isRented);
  }

  public Car() {}

  @Override
  public String toString() {
    return "Car - " + super.toString();
  }
}
