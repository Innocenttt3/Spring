package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {

    private List<User> users;

    public UserRepository() throws CsvValidationException, IOException {
        this.users = new ArrayList<>();
        getUsers();
    }

    @Override
    public void getUsers() throws CsvValidationException, IOException {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (
                FileReader filereader = new FileReader("/Users/kamilgolawski/Nauka/Programowanie/Spring/carRentalWithAuth/users.csv");
                CSVReader csvReader = new CSVReaderBuilder(filereader)
                        .withCSVParser(parser)
                        .build()) {
            String[] singleLine;
            List<User> userProfilesToGet = new ArrayList<>();
            while ((singleLine = csvReader.readNext()) != null) {
                List<String> singleRecord = Arrays.asList(singleLine);
                if (singleRecord.size() == 3) {
                    User user = new User(singleRecord.get(0),
                            singleRecord.get(1),
                            Boolean.parseBoolean(singleRecord.get(2)));
                    userProfilesToGet.add(user);
                } else if (singleRecord.size() == 4) {
                    User user = new User(singleRecord.get(0),
                            singleRecord.get(1),
                            Boolean.parseBoolean(singleRecord.get(2)),
                            Integer.parseInt(singleRecord.get(3)));
                    userProfilesToGet.add(user);
                }
            }
            users = userProfilesToGet;
        }
    }

    @Override
    public void saveUsers(String path) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/kamilgolawski/Nauka/Programowanie/Spring/carRentalWithAuth/users.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (User tmp : users) {
            printWriter.println(tmp.toCSV());
        }
        printWriter.close();
    }

    @Override
    public User getUser(String login) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
        return userOptional.orElse(null);
    }

    @Override
    public void displayUsers() {
        for(User user : users) {
            System.out.println(user.toString());
        }
    }
}
