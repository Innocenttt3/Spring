//package org.example;
//
//import com.opencsv.exceptions.CsvValidationException;
//import org.example.vehicles.Vehicle;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class VehicleRepository implements IVehicleRepository {
//
//    public List<Vehicle> vehicles;
//
//    public VehicleRepository() {
//        vehicles = new ArrayList<>();
//    }
//
//    @Override
//    public void getALlVehicles() {
//        return List.of();
//    }
//
//
//    public boolean vehicleExist(int Id) {
//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.getId() == Id) {
//                System.out.println("Pojazd o tym ID juz istnieje podaj inne:");
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//
//
//    @Override
//    public void save() throws IOException {
//        FileWriter fileWriter = new FileWriter("/Users/kamilgolawski/Nauka/Programowanie/Spring/carRentalWithAuth/vehicles.csv");
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        for (Vehicle tmp : vehicles) {
//            printWriter.println(tmp.toCSV());
//        }
//        printWriter.close();
//    }
//
//
//
//    public void displayAllVehicles() {
//        for (Vehicle tmp : vehicles) {
//            System.out.println(tmp.toString());
//        }
//    }
//
//    public void displayAllAvailableVehicles() {
//        for (Vehicle tmp : vehicles) {
//            if (!tmp.isRented())
//                System.out.println(tmp.toString());
//        }
//    }
//
//    public static void main(String[] args) throws CsvValidationException, IOException {
//
//    }
//}
