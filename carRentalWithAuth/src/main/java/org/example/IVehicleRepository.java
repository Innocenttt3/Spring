package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IVehicleRepository {
    void rentVehicle(int Id);

    void returnVehicle(int Id);

    void getVehicles() throws IOException, CsvValidationException;

    void addVehicle(Vehicle vehicle);

    void removeVehicle(int id);

    void save() throws IOException;

}
