package org.kamilG;

import java.util.Collection;
import org.kamilG.configuration.HibernateUtil;
import org.kamilG.dao.IUserRepo;
import org.kamilG.dao.IVehicleRepo;
import org.kamilG.dao.hibernate.UserDAO;
import org.kamilG.dao.hibernate.VehicleDAO;
import org.kamilG.units.Vehicle;

public class main {
  public static void main(String[] args) {
    IVehicleRepo ivr = VehicleDAO.getInstance(HibernateUtil.getSessionFactory());
    IUserRepo iur = UserDAO.getInstance(HibernateUtil.getSessionFactory());
    Collection<Vehicle> vehicles = ivr.getVehicles();
    System.out.println(vehicles);
  }
}
