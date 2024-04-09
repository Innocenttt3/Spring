package org.example.jdbc;


import org.example.User;

import java.sql.SQLException;
import java.util.Collection;

public interface IUserRepository {
    User getUser(String login);
    void addUser(User user);

    void removeUser(String login);
    void getAllUsers() throws SQLException;

}