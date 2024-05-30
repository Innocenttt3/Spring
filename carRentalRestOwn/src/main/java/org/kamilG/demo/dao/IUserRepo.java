package org.kamilG.demo.dao;

import java.util.Collection;
import org.kamilG.demo.units.User;

public interface IUserRepo {
    User getUser(String login);

    boolean addUser(User user);

    void removeUser(String login);

    Collection<User> getUsers();
}
