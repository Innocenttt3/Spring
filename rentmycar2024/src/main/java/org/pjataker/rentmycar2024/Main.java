package org.pjataker.rentmycar2024;

import org.pjataker.rentmycar2024.Samochod;

public class Main {
    public static void main(String[] args) {
        Samochod mojSamochod = new Samochod("Toyota", "Corolla", 2020);
        Samochod mojSamochod2 = new Samochod("Toyota", "CHR", 2020);
        mojSamochod.wyswietl();
        System.out.println(Samochod.liczbaSamochodow);
        System.out.println(mojSamochod2.getRokProdukcji());
        mojSamochod2.setRokProdukcji(2021);
        System.out.println(mojSamochod2.getRokProdukcji());
        System.out.println(mojSamochod2.toString());

        IRepozytoriumSamochodow repozytoriumSamochodow = new RepozytoriumSamochodowCSV("samochody.csv");

        for (Samochod s : repozytoriumSamochodow.getSamochody()) {
            System.out.println(s);
        }

        System.out.println("===========================================================");
        IRepozytoriumPojazdow repozytoriumPojazdow = new RepozytoriumPojazdow();
        Pojazd sp = new SamochodPotomny("Audi", "A4", 2019);
        SamochodPotomny sp2 = new SamochodPotomny("Audi", "A3", 2017);
        Bus bus = new Bus("Ford", "Transit", 2017, 9);
        repozytoriumPojazdow.dodajPojazd(sp);
        repozytoriumPojazdow.dodajPojazd(sp2);
        repozytoriumPojazdow.dodajPojazd(bus);
        for (Pojazd p : repozytoriumPojazdow.getPojazdy()) {
            p.wyswietl();
        }
    }
}