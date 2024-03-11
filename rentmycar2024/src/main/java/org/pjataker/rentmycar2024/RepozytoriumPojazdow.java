package org.pjataker.rentmycar2024;

import java.util.ArrayList;
import java.util.List;

public class RepozytoriumPojazdow implements IRepozytoriumPojazdow{
    private final List<Pojazd> samochody;

    public RepozytoriumPojazdow() {
        this.samochody = new ArrayList<>();

    }
    @Override
    public void dodajPojazd(Pojazd samochod) {
        samochody.add(samochod);
    }
    @Override
    public boolean usunPojazd(Pojazd samochod) {
        return samochody.remove(samochod);
    }
    @Override
    public List<Pojazd> getPojazdy() {
        return new ArrayList<>(samochody);
    }
}
