package org.example;

import com.opencsv.exceptions.CsvValidationException;
import org.example.jdbc.JdbcUserRepository;
import org.example.jdbc.JdbcVehicleRepository;
import org.example.vehicles.Car;
import org.example.vehicles.Motorcycle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) throws CsvValidationException, IOException, InvalidRentedStatusException, SQLException {
        System.out.println("Witamy w wypożyczalni pojazdów co chcialbyś zrobić ?");
        JdbcVehicleRepository vehicleRepository = new JdbcVehicleRepository();
        JdbcUserRepository userRepository = new JdbcUserRepository();
        Authentication authenticator = new Authentication(userRepository);
        //use only to create new users
        //userRepository.addUser("admin", "admin123", true, null);
        //userRepository.addUser("kamil", "mleko123", false, 1);
        userRepository.getAllUsers();
        int firstInput = 0;
        Scanner firstScanner = new Scanner(System.in);
        while (firstInput != 2) {
            System.out.println("1) Zalogowac sie ");
            System.out.println("2) Wyjsc ");
            firstInput = firstScanner.nextInt();
            switch (firstInput) {
                case 1 -> {
                    if (authenticator.userValidate()) {
                        if (authenticator.typeOfAccount()) {
                            int commandInput = 0;
                            Scanner scanner = new Scanner(System.in);
                            while (commandInput != 5) {
                                System.out.println("1) Wyswietlic liste wszystkich pojazdow");
                                System.out.println("2) Wyswietlic list wszystkich uzytkownikow");
                                System.out.println("3) Dodac pojazd");
                                System.out.println("4) Usunac pojazd");
                                System.out.println("5) Wylogowac sie");
                                commandInput = scanner.nextInt();
                                switch (commandInput) {
                                    case 1 -> {
                                        System.out.println("Wszystkie auta w wypozyczalni:");
                                        vehicleRepository.getAllVehicles();
                                        vehicleRepository.displayAllVehicles();
                                        System.out.println();
                                    }
                                    case 2 -> userRepository.displayUsers();
                                    case 3 -> {
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
                                        String rented = scanner.next();
                                        boolean isRented;
                                        if (rented.equalsIgnoreCase("yes")) {
                                            isRented = true;
                                        } else if (rented.equalsIgnoreCase("no")) {
                                            isRented = false;
                                        } else {
                                            throw new InvalidRentedStatusException("nie obslugiwana odpowiedz");
                                        }
                                        System.out.print("unique id: ");
                                        int uniqueId;
                                        do {
                                            uniqueId = scanner.nextInt();
                                            scanner.nextLine();
                                        } while (vehicleRepository.vehicleExist(uniqueId));
                                        System.out.println("opcjonalne (podaj w przypadku motoru) kategoria:");
                                        String category = scanner.nextLine();
                                        if (category.isEmpty()) {
                                            Car newCar = new Car(brand, model, year, price, isRented, uniqueId);
                                            vehicleRepository.addVehicle(newCar);
                                        } else {
                                            Motorcycle newMotorCycle = new Motorcycle(brand, model, year, price, isRented, uniqueId, category);
                                            vehicleRepository.addVehicle(newMotorCycle);
                                        }
                                        vehicleRepository.save();
                                        System.out.println("Pojazd został dodany.");
                                    }
                                    case 4 -> {
                                        System.out.println("Podaj id pojazdu ktory chcesz usunac");
                                        int IDWeWantToRemove = scanner.nextInt();
                                        vehicleRepository.removeVehicle(IDWeWantToRemove);
                                        vehicleRepository.save();
                                        System.out.println();
                                    }
                                    case 5 -> System.out.println("Poprawnie wylogowano");
                                    default -> System.out.println("Brak takiej opcji");
                                }
                            }
                        } else {
                            int commandInput = 0;
                            Scanner scanner = new Scanner(System.in);
                            while (commandInput != 6) {
                                System.out.println("1) Wyswietlic liste wszystkich pojazdow");
                                System.out.println("2) Wyswietlic liste dostepnych pojazdow");
                                System.out.println("3) Wypozyczyc pojazd");
                                System.out.println("4) Zwrocic pojazd");
                                System.out.println("5) Wyswietlic informacje o koncie");
                                System.out.println("6) Wylogowac sie");
                                commandInput = scanner.nextInt();
                                switch (commandInput) {
                                    case 1 -> {
                                        System.out.println("Wszystkie auta w wypozyczalni");
                                        vehicleRepository.getAllVehicles();
                                        vehicleRepository.displayAllVehicles();
                                        System.out.println();
                                    }
                                    case 2 -> {
                                        System.out.println("Wszystkie auta dostepne");
                                        vehicleRepository.getAllVehicles();
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
                                        System.out.println("Informacje o koncie");
                                        User tmp = userRepository.getUser(authenticator.getCurrentLogin());
                                        System.out.print(tmp.displayInfo());
                                    }
                                    case 6 -> System.out.println("Zegnaj");
                                    default -> System.out.println("Brak takiej opcji");

                                }
                            }
                        }
                    }
                }
                case 2 -> System.out.println("Zegnaj");
                default -> System.out.println("Brak takiej opcji");
            }
        }
    }
}
