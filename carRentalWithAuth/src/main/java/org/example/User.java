package org.example;

public class User {
    private final String login;
    private final String password;
    private Integer idOfRentedVehicle;
    private boolean adminPermission;

    public boolean isAdminPermission() {
        return adminPermission;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User(String login, String password, boolean adminPermission, Integer idOfRentedVehicle) {
        this.login = login;
        this.password = password;
        this.idOfRentedVehicle = idOfRentedVehicle;
        this.adminPermission = adminPermission;
    }
    public User(String login, String password, boolean adminPermission) {
        this.login = login;
        this.password = password;
        this.idOfRentedVehicle = null;
        this.adminPermission = adminPermission;
    }

    public String toCSV() {
        return login + ";" + password + ";" + adminPermission + ";" + idOfRentedVehicle + ";";
    }

    public String toString() {
        return "login: " + login + " password: " + password + " idOfRentedVehicle: " + idOfRentedVehicle + " adminPermission: " + adminPermission;
    }
}
