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
        while (input != 6) {
            System.out.println("1) Wyswietlic liste dostepnych pojazdow");
            System.out.println("2) Wypozyczyc auto");
            System.out.println("3) Zwrocic auto");
            System.out.println("4) Wypozyczyc motor");
            System.out.println("5) Zwrocic motor");
            System.out.println("6) Opuscic wypożyczalnie");
            input = scanner.nextInt();
            switch (input) {
                case 1: {
                    System.out.println("Dostepne auta");
                    vehicleRepository.getVehicles();
                    vehicleRepository.displayVehicles();
                    System.out.println("");
                }
            }
        }

    }
}
