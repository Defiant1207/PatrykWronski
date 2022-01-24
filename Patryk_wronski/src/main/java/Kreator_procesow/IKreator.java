package Kreator_procesow;

public interface IKreator {

    /**
     * Wczytuje dane z pliku
     */
    void load();
    /**
     * sortuje procesy zgodnie z przyjeta metoda
     * @param method - metoda ktora bedziemy sortowac
     */
    void sort(String method);
    /**
     * przypisuje wyniki testu dla odpowiedniej metody i wyswietla jednostkowe wyniki
     * @param method metoda ktorej beda dotyczyly wyniki
     */

    void answers(String method);
}
