package org.kamilG.units;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicles", schema = "lab")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String brand;
  private String model;

  @Column(name = "yearOfProduction")
  private int year;

  private int price;

  @Column(name = "isRented")
  private boolean rented;

  @OneToOne(mappedBy = "rentedVehicle", fetch = FetchType.EAGER)
  private User hirer;

  public Vehicle(int id, String brand, String model, int year, int price) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
  }

  public Vehicle() {}

  public void setHirer(User hirer) {
    this.hirer = hirer;
  }

  public void setRented(boolean rented) {
    this.rented = rented;
  }
}
