package org.pjataker.rentmycar2024;

public class SamochodPotomny extends Pojazd {

    public SamochodPotomny(String marka, String model, int rokProdukcji) {
        super(marka, model, rokProdukcji);
    }

    // Metoda klasy
    @Override
    public void wyswietl() {
        System.out.println("Marka: " + super.getMarka() + ", Model: " + super.getModel()+
                ", Rok Produkcji: " + super.getRokProdukcji() );
    }

    @Override
    public String toString() {
        return "SamochodPotomny{" +
                "marka='" + super.getMarka()  + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", rokProdukcji=" + super.getRokProdukcji() +
                '}';
    }
}
