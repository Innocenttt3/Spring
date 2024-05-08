package org.kamilG.dao.hibernate;

import org.kamilG.dao.IVehicleRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kamilG.units.User;
import org.kamilG.units.Vehicle;

import java.util.Collection;
import java.util.List;

public class VehicleDAO implements org.kamilG.dao.IVehicleRepo {

    private static VehicleDAO instance;
    SessionFactory sessionFactory;

    @Override
    public Vehicle getVehicle(long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Vehicle.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(vehicle);
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
    public void removeVehicle(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Vehicle vehicleToRemove = session.get(Vehicle.class, id);
            if (vehicleToRemove != null) {
                session.remove(vehicleToRemove);
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
    public Collection<Vehicle> getVehicles() {
        Collection<Vehicle> vehicles = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            vehicles = session.createQuery("from Vehicle", Vehicle.class).getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return vehicles;
    }

    @Override
    public boolean rentVehicle(long id, String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, login);
            Vehicle vehicle = session.get(Vehicle.class, id);

            if (user != null && vehicle != null && user.getRentedVehicle() == null) {
                user.setRentedVehicle(vehicle);
                vehicle.setHirer(user);
                vehicle.setRented(true);
                session.saveOrUpdate(user);
                session.saveOrUpdate(vehicle);
                transaction.commit();
                return true;
            } else {
                if (transaction != null) {
                    transaction.rollback();
                }
                return false;
            }
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean returnVehicle(long id, String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, login);
            Vehicle vehicle = session.get(Vehicle.class, id);

            if (user != null && vehicle != null && user.getRentedVehicle() == null) {
                user.setRentedVehicle(vehicle);
                vehicle.setHirer(null);
                vehicle.setRented(false);
                session.saveOrUpdate(user);
                session.saveOrUpdate(vehicle);
                transaction.commit();
                return true;
            } else {
                if (transaction != null) {
                    transaction.rollback();
                }
                return false;
            }
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public VehicleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static VehicleDAO getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new VehicleDAO(sessionFactory);
        }
        return instance;
    }


}
