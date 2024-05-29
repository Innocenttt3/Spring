package org.kamilG.demo.units;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicles", schema = "restApi")
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
        this.rented = false;
    }

    public Vehicle(int id, String brand, String model, int year, int price, boolean isRented) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = isRented;
    }

    public Vehicle() {}

    public void setHirer(User hirer) {
        this.hirer = hirer;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isRented() {
        return rented;
    }

    public String toString() {
        return "id = " + id + ", brand = " + brand + ", model = " + model + ", year = " + year + ", price = " + price + ", rented = " + rented;
    }
}
