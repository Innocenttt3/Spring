package org.pjataker.rentmycar2024;

import java.util.List;

public interface IRepozytoriumPojazdow {

    void dodajPojazd(Pojazd pojazd);

    boolean usunPojazd(Pojazd pojazd);

    List<Pojazd> getPojazdy();
}
