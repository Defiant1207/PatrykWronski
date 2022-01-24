package Kolejka;

import Procesy.IProcesy;

public interface IKolejka {
    /**
     *
     * @param x - pozycja w tabeli
     * @return czas przyjscia procesu
     */
    double getProcessA(int x);
    /**
     *
     * @param x - pozycja w tabeli
     * @return czas przetwarzania procesu
     */
    double getProcessP(int x);
    /**
     * Sortuje procesy zaczynajac od tych, ktore najwczesniej przyszly
     */
    void sort();
    /**
     *
     * @param process
     * @return pozycja na ktorej znajduje sie proces
     */

    int getProcessPosition(IProcesy process);
    /**
     * umieszcza proces na podanej pozycji
     * @param process
     * @param x - pozycja na ktorej chcemy umiescic proces
     * @return prawda- jezeli mozna umiescic proces na danej pozycji, falsz- nie mozna
     */
    boolean settleProcess(IProcesy process, int x);
    /**
     * zlicza czasy oczekiwania i przetwarzania procesow
     * @return laczny czas oczekiwania i przetwarzania procesow
     */
    double[] waitingTime();
    /**
     *
     * @param i - pozycja procesu w tabeli
     * @param t - aktualny czas
     * decyduje, ktory proces dodac do kolejki
     */
    void whoIsNextSJF(int i, double t);

    /**
     * sortuje malejąco kolejke zawierajaca procesy gotowe do wykonania
     */
    void sortKolejka();

    /**
     * Sortuje procesy metoda SJF
     */
    void sortSJF();
}
