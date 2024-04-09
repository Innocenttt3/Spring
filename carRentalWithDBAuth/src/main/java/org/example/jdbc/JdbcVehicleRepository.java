package org.example.jdbc;

import org.example.vehicles.Car;
import org.example.vehicles.Motorcycle;
import org.example.vehicles.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcVehicleRepository implements IVehicleRepository {
    private static JdbcVehicleRepository instance;
    private final DatabaseManager databaseManager;
    public Collection<Vehicle> vehicles;
    private final String GET_ALL_VEHICLE_SQL = "SELECT * FROM lab4Zad.vehicle";
    private final String INSERT_VEHICLE_SQL = "INSERT INTO lab4Zad.vehicle (brand, model, year, price, id, isRented) VALUES (?,?,?,?,?, ?)";
    ;

    public static JdbcVehicleRepository getInstance() {
        if (JdbcVehicleRepository.instance == null) {
            instance = new JdbcVehicleRepository();
        }
        return instance;
    }

    private JdbcVehicleRepository() {
        this.databaseManager = DatabaseManager.getInstance();
        this.vehicles = new ArrayList<>();
    }


    @Override
    public void getAllVehicles() {
        Collection<Vehicle> vehiclesFetched = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_VEHICLE_SQL);
            while (resultSet.next()) {
                Vehicle tmp = null;
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                int price = resultSet.getInt("price");
                boolean isRented = resultSet.getBoolean("isRented");
                String category = resultSet.getString("category");
                int id = resultSet.getInt("id");
                if (category != null) {
                    tmp = new Motorcycle(brand, model, year, price, isRented, id, category);
                } else {
                    tmp = new Car(brand, model, year, price, isRented, id);
                }
                vehiclesFetched.add(tmp);
            }
            vehicles = vehiclesFetched;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void save() throws SQLException {
        Connection connection = databaseManager.getConnection();
        for (Vehicle tmp : vehicles) {
            PreparedStatement statement = connection.prepareStatement(INSERT_VEHICLE_SQL);
            statement.setString(1, tmp.getBrand());
            statement.setString(2, tmp.getModel());
            statement.setInt(3, tmp.getYear());
            statement.setInt(4, tmp.getPrice());
            statement.setInt(5, tmp.getId());
            statement.setBoolean(6, tmp.isRented());
            int changed = statement.executeUpdate();
            if (changed  > 0) {
                System.out.println("Pojazd został pomyślnie dodany.");
            } else {
                System.out.println("Nie udało się dodać pojazdu.");
            }
        }

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
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(int id) {
        vehicles.removeIf(v -> v.getId() == id);
    }

    public static void main(String[] args) throws SQLException {
        JdbcVehicleRepository tmp = new JdbcVehicleRepository();
        Car testowe = new Car("test", "test", 2000, 120, false, 2137);
        tmp.vehicles.add(testowe);
        tmp.save();

    }
}
