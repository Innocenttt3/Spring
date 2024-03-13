package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IVehicleRepository {
    void rentCar(int Id);

    void rentMotorcycle(int Id);

    void returnCar(int Id);

    void returnMotorcycle(int Id);

    void getVehicles() throws FileNotFoundException, IOException, CsvValidationException;

    void save() throws IOException;

}
