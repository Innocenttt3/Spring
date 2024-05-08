package org.kamilG.dao;

import org.kamilG.units.User;

import java.util.Collection;

public interface IUserRepo {
    User getUser(String login);

    void addUser(User user);

    void removeUser(String login);

    Collection<User> getUsers();
}
