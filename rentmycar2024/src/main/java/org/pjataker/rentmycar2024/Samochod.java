package org.pjataker.rentmycar2024;

public class Samochod {
    public static int liczbaSamochodow = 0;
    //...
    private String marka;
    private String model;
    private int rokProdukcji;

    public Samochod(String marka, String model, int rokProdukcji) {
        liczbaSamochodow++;
        //...
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
    }

    // Metoda klasy
    public void wyswietl() {
        System.out.println("Marka: " + marka + ", Model: " + model +
                ", Rok Produkcji: " + rokProdukcji);
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                '}';
    }
}
