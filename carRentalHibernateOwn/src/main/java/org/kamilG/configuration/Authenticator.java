package org.kamilG.configuration;

import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;
import org.kamilG.dao.IUserRepo;
import org.kamilG.dao.hibernate.UserDAO;
import org.kamilG.units.User;

public class Authenticator {
  public static User login(String login, String password) {
    IUserRepo jur = UserDAO.getInstance(HibernateUtil.getSessionFactory());
    User userFromDb = jur.getUser(login);
    if (userFromDb != null && (password).equals(userFromDb.getPassword())) {
      return userFromDb;
    }
    return null;
  }

  // TODO hashed password
  public static String hashPassword(String password) {
    return DigestUtils.sha256Hex(password);
  }

  public static User userValidate() {
    Scanner scanner = new Scanner(System.in);
    int maxAttempts = 3;
    User user = null;

    while (maxAttempts > 0) {
      System.out.println("Podaj login");
      String login = scanner.nextLine();
      System.out.println("Podaj haslo");
      String password = scanner.nextLine();

      user = login(login, password);
      if (user != null) {
        System.out.println("Zalogowano pomyslnie");
        return user;
      } else {
        maxAttempts--;
        System.out.println("Bledne dane, pozostalo prob: " + maxAttempts);
      }
    }
    return null;
  }
}
