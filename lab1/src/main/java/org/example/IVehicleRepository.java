package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IVehicleRepository {
    void rentCar(Car car);

    void returnCar(Car car);

    void getVehicles(String path) throws FileNotFoundException, IOException, CsvValidationException;

    void save(String path);

}
