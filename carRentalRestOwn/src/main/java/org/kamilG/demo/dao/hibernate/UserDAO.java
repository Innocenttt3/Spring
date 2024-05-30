package org.kamilG.demo.dao.hibernate;

import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kamilG.demo.dao.IUserRepo;
import org.kamilG.demo.units.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserRepo {

  private static UserDAO instance;
  private SessionFactory sessionFactory;

  @Autowired
  public UserDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public User getUser(String login) {
    Session session = sessionFactory.openSession();
    try {
      return session.get(User.class, login);
    } finally {
      session.close();
    }
  }

  @Override
  public boolean addUser(User user) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.merge(user);
      transaction.commit();
      return true;
    } catch (RuntimeException e) {
      if (transaction != null) {
        transaction.rollback();
        return false;
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return false;
  }

  @Override
  public void removeUser(String login) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      User userToRemove = session.get(User.class, login);
      if (userToRemove != null) {
        session.remove(userToRemove);
      } else {
        return;
      }
      transaction.commit();
    } catch (RuntimeException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  @Override
  public Collection<User> getUsers() {
    Collection<User> users = null;
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      users = session.createQuery("FROM User", User.class).getResultList();
      transaction.commit();
    } catch (RuntimeException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
    return users;
  }

  public static UserDAO getInstance(SessionFactory sessionFactory) {
    if (instance == null) {
      instance = new UserDAO(sessionFactory);
    }
    return instance;
  }
}
