package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {

    public List<Vehicle> vehicles;

    public VehicleRepository() {
        vehicles = new ArrayList<>();
    }


    @Override
    public void rentCar(int Id) {

    }

    @Override
    public void rentMotorcycle(int Id) {

    }

    @Override
    public void returnCar(int Id) {

    }

    @Override
    public void returnMotorcycle(int Id) {

    }


    @Override
    public void getVehicles() throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader("/Users/kamilgolawski/Nauka/Programowanie/Spring/lab1/vehicles.csv"))) {
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
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/kamilgolawski/Nauka/Programowanie/Spring/lab1/vehicles.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Vehicle tmp : vehicles) {
            printWriter.println(tmp.toCSV());
        }
        printWriter.close();
    }

    public void displayVehicles() {
        for (Vehicle tmp : vehicles) {
            System.out.println(tmp.toString());
        }
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        VehicleRepository tmp = new VehicleRepository();
        tmp.getVehicles();
        for (Vehicle vehicle : tmp.vehicles) {
            System.out.println(vehicle.toString());
        }
        tmp.save();
    }
}
