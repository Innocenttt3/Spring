package org.pjataker.rentmycar2024;

public abstract class Pojazd {
    //...
    private String marka;
    private String model;
    private int rokProdukcji;

    public Pojazd(String marka, String model, int rokProdukcji) {
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
    }

    // Metoda klasy
//    public void wyswietl() {
//        System.out.println("Marka: " + marka + ", Model: " + model +
//                ", Rok Produkcji: " + rokProdukcji);
//    }

    public abstract void wyswietl();
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

}
