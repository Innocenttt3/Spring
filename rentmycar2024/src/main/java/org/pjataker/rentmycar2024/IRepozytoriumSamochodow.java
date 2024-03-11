package org.pjataker.rentmycar2024;

import java.util.ArrayList;
import java.util.List;

public interface IRepozytoriumSamochodow {

    void dodajSamochod(Samochod samochod);

    boolean usunSamochod(Samochod samochod);

    List<Samochod> getSamochody();
}
