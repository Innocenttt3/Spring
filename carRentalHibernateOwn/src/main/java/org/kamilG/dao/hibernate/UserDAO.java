package org.kamilG.dao.hibernate;

import org.kamilG.dao.IUserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kamilG.units.User;

import java.util.Collection;


public class UserDAO implements org.kamilG.dao.IUserRepo {

    private static UserDAO instance;
    SessionFactory sessionFactory;

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(User.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(user);
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
    public void removeUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User userToRemove = session.get(User.class, id);
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
            if (transaction != null) {transaction.rollback();}
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    private UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserDAO getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new UserDAO(sessionFactory);
        }
        return instance;
    }
}
