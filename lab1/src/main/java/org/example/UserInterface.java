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
        while (input != 5) {
            System.out.println("1) Wyswietlic liste wszystkich pojazdow");
            System.out.println("2) Wyswietlic liste dostepnych pojazdow");
            System.out.println("3) Wypozyczyc pojazd");
            System.out.println("4) Zwrocic pojazd");
            System.out.println("5) Opuscic wypożyczalnie");
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
                    System.out.println("Zegnaj");
                }
                default -> System.out.println("Brak takiej opcji");
            }
        }

    }
}
