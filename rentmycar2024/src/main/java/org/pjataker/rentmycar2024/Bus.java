package org.pjataker.rentmycar2024;

public class Bus extends Pojazd {
    private int miejscaSiedzace;
    public Bus(String marka, String model, int rokProdukcji,int miejscaSiedzace) {
        super(marka, model, rokProdukcji);
        this.miejscaSiedzace = miejscaSiedzace;

    }

    // Metoda klasy
    @Override
    public void wyswietl() {
        System.out.println("Marka: " + super.getMarka() + ", Model: " + super.getModel()+
                ", Rok Produkcji: " + super.getRokProdukcji() +" Miejsca siedzace: "+ this.miejscaSiedzace );
    }

    @Override
    public String toString() {
        return "Bus{" +
                "marka='" + super.getMarka()  + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", rokProdukcji=" + super.getRokProdukcji() +
                ", miejscaSiedzace=" + this.miejscaSiedzace +
                '}';
    }


}
