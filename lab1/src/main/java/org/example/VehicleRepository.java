package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {

    public List<Vehicle> vehicles;

    public VehicleRepository() {
        vehicles = new ArrayList<>();
    }


    @Override
    public void rentCar(Car car) {

    }

    @Override
    public void returnCar(Car car) {

    }

    @Override
    public void getVehicles(String path) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            String[] singleLine;
            while ((singleLine = csvReader.readNext()) != null) {
                List<String> singleRecord = Arrays.asList(singleLine);
                if (singleRecord.size() == 6) {
                    Car tmpCar = new Car(singleRecord.get(0),
                            singleRecord.get(1),
                            Integer.parseInt(singleRecord.get(2)),
                            Integer.parseInt(singleRecord.get(3)),
                            Boolean.parseBoolean(singleRecord.get(4)),
                            Integer.parseInt(singleRecord.get(5)));
                    vehicles.add(tmpCar);
                } else if (singleRecord.size() == 7) {
                    Motorcycle tmpMotorCycle = new Motorcycle(singleRecord.get(0),
                            singleRecord.get(1),
                            Integer.parseInt(singleRecord.get(2)),
                            Integer.parseInt(singleRecord.get(3)),
                            Boolean.parseBoolean(singleRecord.get(4)),
                            Integer.parseInt(singleRecord.get(5)),
                            singleRecord.get(6));
                    vehicles.add(tmpMotorCycle);
                }
            }
        }
    }

    @Override
    public void save(String path) {

    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        VehicleRepository tmp = new VehicleRepository();
        tmp.getVehicles("/Users/kamilgolawski/Nauka/Programowanie/Spring/lab1/vehicles.csv");
        for (Vehicle vehicle : tmp.vehicles) {
            System.out.println(vehicle.toString());
        }
    }
}
