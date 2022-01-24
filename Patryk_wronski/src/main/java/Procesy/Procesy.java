package Procesy;

import Kolejka.IKolejka;

public class Procesy extends AProcesy {


    public Procesy(double czas_przybycia, double czas_przetwarzania, IKolejka kolejka) {
        super(czas_przybycia, czas_przetwarzania, kolejka);
    }
}
