package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface IUserRepository {

    void getUsers() throws CsvValidationException, IOException;

    void saveUsers(String path) throws IOException;

    User getUser(String path);

    void displayUsers();
}
