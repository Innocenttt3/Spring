package org.pjataker.rentmycar2024;

import java.util.ArrayList;
import java.util.List;

public class RepozytoriumSamochodow implements IRepozytoriumSamochodow{
    private final List<Samochod> samochody;

    public RepozytoriumSamochodow() {
        this.samochody = new ArrayList<>();

    }
    @Override
    public void dodajSamochod(Samochod samochod) {
        samochody.add(samochod);
    }
    @Override
    public boolean usunSamochod(Samochod samochod) {
        return samochody.remove(samochod);
    }
    @Override
    public List<Samochod> getSamochody() {
        return new ArrayList<>(samochody);
    }
}
