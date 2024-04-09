package org.example.jdbc;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.User;
import org.example.vehicles.Car;
import org.example.vehicles.Motorcycle;
import org.example.vehicles.Vehicle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class JdbcUserRepository implements IUserRepository {
    private static JdbcUserRepository instance;
    private final DatabaseManager databaseManager;
    private Collection<User> users;

    private static String GET_ALL_USERS_SQL = "SELECT * FROM lab4zad.user";

    private JdbcUserRepository() throws SQLException {
        this.databaseManager = DatabaseManager.getInstance();
        this.users = new ArrayList<>();
        getAllUsers();
    }

    @Override
    public User getUser(String login) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void removeUser(String login) {

    }

    @Override
    public void getAllUsers() {
        Collection<User> userProfilesToGet = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = databaseManager.getConnection();
            connection.createStatement();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS_SQL);
            while (resultSet.next()) {
                User tmp = null;
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Integer id = resultSet.getInt("idOfRentedVehicle");
                boolean isAdmin = resultSet.getBoolean("adminPermission");
                tmp = new User(login, password, isAdmin, id);
                userProfilesToGet.add(tmp);
            }
            users = userProfilesToGet;
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

    public static void main(String[] args) throws SQLException {
        JdbcUserRepository tmp = new JdbcUserRepository();
        for(User user: tmp.users){
            System.out.println(user.toString());
        }

    }
}
