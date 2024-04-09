package org.example.jdbc;


import org.example.User;

import java.sql.SQLException;
import java.util.Collection;

public interface IUserRepository {
    User getUser(String login);
    void removeUser(String login) throws SQLException;
    void getAllUsers() throws SQLException;
    void saveUsers() throws SQLException;
    void addUser(String username, String password, boolean isAdmin, Integer idOfRentedCard) throws SQLException;

}