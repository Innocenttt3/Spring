package org.kamilG.demo.dao;

import org.kamilG.demo.units.Vehicle;

import java.util.Collection;


public interface IVehicleRepo {
    boolean rentVehicle(long id, String login);

    boolean returnVehicle(long id, String login);

    void addVehicle(Vehicle vehicle);

    void removeVehicle(long id);

    Vehicle getVehicle(long id);

    Collection<Vehicle> getVehicles();
}
