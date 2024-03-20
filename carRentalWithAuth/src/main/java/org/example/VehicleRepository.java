package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
    public void rentVehicle(int Id) {
        boolean vehicleFound = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == Id && !vehicle.isRented()) {
                vehicle.setRented(true);
                vehicleFound = true;
                break;
            }
        }
        if (!vehicleFound) {
            System.out.println("Nie znaleziono pojazdu o ID: " + Id + " lub pojazd jest już wynajęty.");
        }
    }


    @Override
    public void returnVehicle(int Id) {
        boolean vehicleFound = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == Id && vehicle.isRented()) {
                vehicle.setRented(false);
                vehicleFound = true;
                break;
            }
        }
        if (!vehicleFound) {
            System.out.println("Nie znaleziono pojazdu o ID: " + Id + " lub pojazd nie jest wynajęty.");
        }
    }


    @Override
    public void getVehicles() throws IOException, CsvValidationException {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (
                FileReader filereader = new FileReader("/Users/kamilgolawski/Nauka/Programowanie/Spring/carRentalWithAuth/vehicles.csv");
                CSVReader csvReader = new CSVReaderBuilder(filereader)
                        .withCSVParser(parser)
                        .build()){
            String[] singleLine;
            List<Vehicle> vehiclesToGet = new ArrayList<>();
            while ((singleLine = csvReader.readNext()) != null) {
                List<String> singleRecord = Arrays.asList(singleLine);
                if (singleRecord.size() == 6) {
                    Car tmpCar = new Car(singleRecord.get(0),
                            singleRecord.get(1),
                            Integer.parseInt(singleRecord.get(2)),
                            Integer.parseInt(singleRecord.get(3)),
                            Boolean.parseBoolean(singleRecord.get(4)),
                            Integer.parseInt(singleRecord.get(5)));
                    vehiclesToGet.add(tmpCar);
                } else if (singleRecord.size() == 7) {
                    Motorcycle tmpMotorCycle = new Motorcycle(singleRecord.get(0),
                            singleRecord.get(1),
                            Integer.parseInt(singleRecord.get(2)),
                            Integer.parseInt(singleRecord.get(3)),
                            Boolean.parseBoolean(singleRecord.get(4)),
                            Integer.parseInt(singleRecord.get(5)),
                            singleRecord.get(6));
                    vehiclesToGet.add(tmpMotorCycle);
                }
            }
            vehicles = vehiclesToGet;
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(int id) {
        vehicles.removeIf(v -> v.getId() == id);
    }


    @Override
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/kamilgolawski/Nauka/Programowanie/Spring/carRentalWithAuth/vehicles.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Vehicle tmp : vehicles) {
            printWriter.println(tmp.toCSV());
        }
        printWriter.close();
    }

    public void displayAllVehicles() {
        for (Vehicle tmp : vehicles) {
            System.out.println(tmp.toString());
        }
    }

    public void displayAllAvailableVehicles() {
        for (Vehicle tmp : vehicles) {
            if (!tmp.isRented())
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
