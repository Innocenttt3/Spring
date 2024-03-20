package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {


    public static void main(String[] args) throws CsvValidationException, IOException {
        System.out.println("Witamy w wypożyczalni pojazdów co chcialbyś zrobić ?");
        VehicleRepository vehicleRepository = new VehicleRepository();
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        while (input != 7) {
            System.out.println("1) Wyswietlic liste wszystkich pojazdow");
            System.out.println("2) Wyswietlic liste dostepnych pojazdow");
            System.out.println("3) Wypozyczyc pojazd");
            System.out.println("4) Zwrocic pojazd");
            System.out.println("5) Usunac pojazd");
            System.out.println("6) Dodac pojazd");
            System.out.println("7) Opuscic wypożyczalnie");
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("Wszystkie auta w wypozyczalni");
                    vehicleRepository.getVehicles();
                    vehicleRepository.displayAllVehicles();
                    System.out.println();

                }
                case 2 -> {
                    System.out.println("Wszystkie auta dostepne");
                    vehicleRepository.getVehicles();
                    vehicleRepository.displayAllAvailableVehicles();
                    System.out.println();

                }
                case 3 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz wypozyczyc");
                    int IDWeWantToRent = scanner.nextInt();
                    vehicleRepository.rentVehicle(IDWeWantToRent);
                    vehicleRepository.save();
                    System.out.println();
                }
                case 4 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz zwrocic");
                    int IDWeWantToReturn = scanner.nextInt();
                    vehicleRepository.returnVehicle(IDWeWantToReturn);
                    vehicleRepository.save();
                    System.out.println();
                }
                case 5 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz usunac");
                    int IDWeWantToRemove = scanner.nextInt();
                    vehicleRepository.removeVehicle(IDWeWantToRemove);
                    vehicleRepository.save();
                    System.out.println();
                }
                case 6 -> {
                    System.out.println("Podaj dane pojazdu ktory chcesz dodac");
                    System.out.print("brand: ");
                    String brand = scanner.next();
                    System.out.print("model: ");
                    String model = scanner.next();
                    System.out.print("year: ");
                    int year = scanner.nextInt();
                    System.out.print("price: ");
                    int price = scanner.nextInt();
                    System.out.print("is it already rented: ");
                    boolean rented = scanner.nextBoolean();
                    System.out.print("unique id: ");
                    int uniqueId = -1;
                    while (true) {
                        uniqueId = scanner.nextInt();
                        int finalUniqueId = uniqueId;
                        boolean idExists = vehicleRepository.vehicles.stream().anyMatch(vehicle -> vehicle.getId() == finalUniqueId);
                        if (!idExists) {
                            break;
                        } else {
                            System.out.println("Takie ID już istnieje, prosze wprowadzic inne");
                        }
                    }
                    System.out.println("opcjonalne (podaj w przypadku motoru) kategoria:");
                    String category = scanner.nextLine();
                    if(category.isEmpty()){
                        Car newCar = new Car(brand, model, year, price, rented, uniqueId);
                        vehicleRepository.addVehicle(newCar);
                    } else {
                        Motorcycle newMotorCycle = new Motorcycle(brand, model, year, price, rented, uniqueId, category);
                        vehicleRepository.addVehicle(newMotorCycle);
                    }
                    vehicleRepository.save();
                }
                case 7 -> System.out.println("Zegnaj");
                default -> System.out.println("Brak takiej opcji");
            }
        }

    }
}
