package org.kamilG.dao;

import org.kamilG.units.User;

import java.util.Collection;

public interface IUserRepo {
    User getUser(long id);

    void addUser(User user);

    void removeUser(long id);

    Collection<User> getUsers();
}
