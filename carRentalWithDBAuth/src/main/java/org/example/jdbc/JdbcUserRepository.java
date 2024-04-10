package org.example.jdbc;

import org.example.Authentication;
import org.example.User;

import java.sql.*;
import java.util.*;

public class JdbcUserRepository implements IUserRepository {
    private final DatabaseManager databaseManager;
    private Collection<User> users;

    private static String GET_ALL_USERS_SQL = "SELECT * FROM lab4zad.user";
    private static String INSERT_USER_SQL = "INSERT INTO lab4Zad.user (login, password, idOfRentedVehicle, adminPermission) VALUES (?, ?, ?, ?)";
    private static String DELETE_USER_SQL = "DELETE FROM lab4zad.user WHERE login = ?";
    public JdbcUserRepository() throws SQLException {
        this.databaseManager = DatabaseManager.getInstance();
        this.users = new ArrayList<>();
        getAllUsers();
    }

    public void displayUsers() {
        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Override
    public User getUser(String login) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
        return userOptional.orElse(null);
    }

    @Override
    public void removeUser(String login) throws SQLException {
        Connection connection = databaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
        statement.setString(1, login);
        int changed = statement.executeUpdate();
        if (changed  > 0) {
            System.out.println("Uzytkownik:" + login + " pomyslnie usuniety");
        } else {
            System.out.println("Nie udało się usunac uzytkownika");
        }
    }

    @Override
    public void saveUsers() throws SQLException {
        Connection connection = databaseManager.getConnection();
        for(User tmpUser : users) {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
            statement.setString(1, tmpUser.getLogin());
            statement.setString(2, tmpUser.getPassword());
            statement.setInt(3, tmpUser.getIdOfRentedVehicle());
            statement.setBoolean(4, tmpUser.isAdminPermission());
            int changed = statement.executeUpdate();
            if (changed  > 0) {
                System.out.println("Uzytkownicy zostali zapisani");
            } else {
                System.out.println("Nie udało się zapisac uzytkownikow");
            }
        }
    }

    @Override
    public void addUser(String username, String password, boolean isAdmin, Integer idOfRentedCard) throws SQLException {
        Connection connection = databaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
        statement.setString(1, username);
        String hashedPassword = Authentication.getSHA256Hash(password);
        statement.setString(2, hashedPassword);
        if (idOfRentedCard == null) {
            statement.setNull(3, Types.INTEGER);
        } else {
            statement.setInt(3, idOfRentedCard);
        }
        statement.setBoolean(4, isAdmin);
        int changed = statement.executeUpdate();
        if (changed  > 0) {
            System.out.println("Nowy uzytkownik zostal utworzony");
        } else {
            System.out.println("Nie udało się utworzyc nowego uzytkownika");
        }
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
