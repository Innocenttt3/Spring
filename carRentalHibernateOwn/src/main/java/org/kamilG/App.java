package org.kamilG;

import java.util.Scanner;
import org.kamilG.configuration.Authenticator;
import org.kamilG.configuration.HibernateUtil;
import org.kamilG.dao.IUserRepo;
import org.kamilG.dao.IVehicleRepo;
import org.kamilG.dao.hibernate.UserDAO;
import org.kamilG.dao.hibernate.VehicleDAO;
import org.kamilG.units.Car;
import org.kamilG.units.Motorcycle;
import org.kamilG.units.User;
import org.kamilG.units.Vehicle;

public class App {
  public static User user = null;
  private final Scanner scanner = new Scanner(System.in);
  IUserRepo iur = UserDAO.getInstance(HibernateUtil.getSessionFactory());
  IVehicleRepo ivr = VehicleDAO.getInstance(HibernateUtil.getSessionFactory());

  public void run() throws InvalidRentedStatusException {
    System.out.println("Witamy w wypożyczalni pojazdów co chcialbyś zrobić ?");
    int firstInput = 0;
    while (firstInput != 2) {
      System.out.println("1) Zalogowac sie ");
      System.out.println("2) Wyjsc ");
      firstInput = scanner.nextInt();
      switch (firstInput) {
        case 1 -> {
          user = Authenticator.userValidate();
          if (user != null) {
            if (user.getRole() == User.Role.ADMIN) {
              int commandInput = 0;
              while (commandInput != 7) {
                System.out.println("1) Wyswietlic liste wszystkich pojazdow");
                System.out.println("2) Wyswietlic list wszystkich uzytkownikow");
                System.out.println("3) Dodac pojazd");
                System.out.println("4) Usunac pojazd");
                System.out.println("5) Dodac uzytkownika");
                System.out.println("6) Usunac uzytkownika");
                System.out.println("7) Wylogowac sie");
                commandInput = scanner.nextInt();
                switch (commandInput) {
                  case 1 -> {
                    System.out.println("Wszystkie auta w wypozyczalni:");
                    for (Vehicle v : ivr.getVehicles()) {
                      System.out.println(v);
                    }
                  }
                  case 2 -> {
                    System.out.println("Wszyscy uzytkowicy w bazie:");
                    for (User u : iur.getUsers()) {
                      System.out.println(u);
                    }
                  }
                  case 3 -> {
                    System.out.println("Podaj dane pojazdu ktory chcesz dodac");
                    System.out.print("unique id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("model: ");
                    String model = scanner.nextLine();
                    System.out.print("year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("is it already rented: ");
                    String rented = scanner.nextLine();
                    boolean isRented;
                    if (rented.equalsIgnoreCase("yes")) {
                      isRented = true;
                    } else if (rented.equalsIgnoreCase("no")) {
                      isRented = false;
                    } else {
                      throw new InvalidRentedStatusException("nie obslugiwana odpowiedz");
                    }
                    System.out.println("opcjonalne (podaj w przypadku motoru) kategoria:");
                    String category = scanner.nextLine();
                    if (category.isEmpty()) {
                      Car newCar = new Car(id, brand, model, year, price, isRented);
                      ivr.addVehicle(newCar);
                    } else {
                      Motorcycle newMotorCycle =
                          new Motorcycle(id, brand, model, year, price, isRented, category);
                      ivr.addVehicle(newMotorCycle);
                    }
                  }

                  case 4 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz usunac");
                    int idToRemove = scanner.nextInt();
                    ivr.removeVehicle(idToRemove);
                  }
                  case 5 -> {
                    System.out.println("Podaj unikalny login nowego uzytkownika");
                    String login = scanner.next();
                    System.out.println("Podaj haslo nowego uzytkownika");
                    String password = scanner.next();
                    User newUser = new User(login, password);
                    iur.addUser(newUser);
                  }
                  case 6 -> {
                    String loginToRemove;
                    boolean userExists = false;
                    do {
                      System.out.println("Podaj login uzytkownika ktorego chcesz usunac");
                      loginToRemove = scanner.next();

                      if (iur.getUser(loginToRemove) != null) {
                        userExists = true;
                      } else {
                        System.out.println("Nie ma takiego uzytkownika w bazie. Spróbuj ponownie.");
                      }
                    } while (!userExists);
                  }
                  case 7 -> {
                    System.out.println("Wylogowano");
                    user = null;
                  }
                  default -> System.out.println("Brak takiej opcji");
                }
              }
            } else if (user.getRole() == User.Role.USER) {
              int commandInput = 0;
              Scanner scanner = new Scanner(System.in);
              while (commandInput != 5) {
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

                    System.out.println();
                  }
                  case 2 -> {
                    System.out.println("Wszystkie auta dostepne");
                    ivr.getVehicles().stream()
                        .filter(Vehicle::isRented)
                        .forEach(System.out::println);
                    System.out.println();
                  }
                  case 3 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz wypozyczyc");
                    int idToRent = scanner.nextInt();
                    ivr.rentVehicle(idToRent, user.getLogin());
                    System.out.println();
                  }
                  case 4 -> {
                    System.out.println("Podaj id pojazdu ktory chcesz zwrocic");
                    int idToRent = scanner.nextInt();
                    ivr.returnVehicle(idToRent, user.getLogin());
                    System.out.println();
                  }
                  case 5 -> System.out.println("Zegnaj");
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
