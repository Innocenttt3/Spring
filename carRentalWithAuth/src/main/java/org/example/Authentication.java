package org.example;

import java.util.Scanner;

public class Authentication {
    private UserRepository userRepository;
    private String currentLogin;

    public Authentication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean typeOfAccount() {
        return userRepository.getUser(currentLogin).isAdminPermission();
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
                    passwordCorrect = user.getPassword().equals(inputPassword);

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
