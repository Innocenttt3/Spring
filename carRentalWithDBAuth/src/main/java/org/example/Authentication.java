package org.example;

import org.example.jdbc.JdbcUserRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Authentication {
    private JdbcUserRepository userRepository;
    private String currentLogin;

    public Authentication(JdbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean typeOfAccount() {
        return userRepository.getUser(currentLogin).isAdminPermission();
    }

    public String getCurrentLogin() {
        return currentLogin;
    }

    public void creatUser(String username, String password, boolean isAdmin, Integer idOfRentedCard, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

            String adminHashedPassword = getSHA256Hash(password);
            if (idOfRentedCard == null) {
                writer.write(username + ";" + adminHashedPassword + ";" + isAdmin);
            } else {
                writer.write(username + ";" + adminHashedPassword + ";" + isAdmin + ";" + idOfRentedCard);
            }
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSHA256Hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userValidate() {
        Scanner scanner = new Scanner(System.in);
        String inputLogin, inputPassword;
        boolean userFound = false, passwordCorrect = false;
        int maxAttempts = 3;

        while (!userFound && maxAttempts > 0) {
            System.out.print("Podaj login: ");
            inputLogin = scanner.nextLine();
            User user = userRepository.getUser(inputLogin);
            userFound = user != null;

            if (userFound) {
                currentLogin = inputLogin;
                int attempts = 0;
                while (!passwordCorrect && attempts < maxAttempts) {
                    System.out.print("Podaj hasło: ");
                    inputPassword = scanner.nextLine();
                    String hashedInputPassword = getSHA256Hash(inputPassword);
                    passwordCorrect = user.getPassword().equals(hashedInputPassword);

                    if (!passwordCorrect) {
                        System.out.println("Nieprawidłowe hasło, spróbuj ponownie.");
                        attempts++;
                    }
                }

                if (!passwordCorrect) {
                    System.out.println("Przekroczyłeś maksymalną liczbę prób logowania.");
                    maxAttempts--;
                }
            } else {
                System.out.println("Nieprawidłowy login, spróbuj ponownie.");
            }
        }

        if (userFound && passwordCorrect) {
            System.out.println("Zalogowano pomyślnie.");
            return true;
        } else {
            System.out.println("Nie udało się zalogować.");
            return false;
        }
    }
}
