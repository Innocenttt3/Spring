package org.pjataker.rentmycar2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RepozytoriumSamochodowCSV implements IRepozytoriumSamochodow {

    private final List<Samochod> samochody  = new ArrayList<>();;

    public RepozytoriumSamochodowCSV(String sciezka) {
        wczytajCSV(sciezka);
    }

    @Override
    public void dodajSamochod(Samochod samochod) {

    }

    @Override
    public boolean usunSamochod(Samochod samochod) {
        return false;
    }

    @Override
    public List<Samochod> getSamochody() {
        return new ArrayList<>(samochody);
    }

    private void wczytajCSV(String sciezka) {
        File plikCSV = new File(sciezka);
        try (Scanner scanner = new Scanner(plikCSV)) {
            while (scanner.hasNextLine()) {
                String linia = scanner.nextLine();
                String[] dane = linia.split(",");
                Samochod samochod = new Samochod(dane[0], dane[1], Integer.parseInt(dane[2]));
                samochody.add(samochod);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Plik CSV nie został znaleziony: " + e.getMessage());
        }
    }
}
