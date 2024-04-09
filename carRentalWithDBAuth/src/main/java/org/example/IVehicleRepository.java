package org.example;

import org.example.vehicles.Vehicle;

import java.io.IOException;
import java.sql.SQLException;

public interface IVehicleRepository {

    void getALlVehicles();

    void rentVehicle(int Id);

    void returnVehicle(int Id);

    void addVehicle(Vehicle vehicle);

    void removeVehicle(int id);

    void save() throws IOException, SQLException;
}
