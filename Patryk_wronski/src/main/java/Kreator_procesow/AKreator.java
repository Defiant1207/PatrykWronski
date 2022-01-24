package Kreator_procesow;

import Kolejka.IKolejka;
import Kolejka.Kolejka;
import Procesy.IProcesy;
import Procesy.Procesy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AKreator implements IKreator {

    double a[];
    double P[];
    List<IProcesy> procesy;
    Scanner scanner;
    double[] odp;
    IKolejka q1;

    public AKreator(File dane) throws FileNotFoundException {
        this.scanner = new Scanner(dane);
        this.odp = new double[4];
        this.P = new double[15];
        this.a = new double[15];
        this.q1 = new Kolejka(15);
        this.procesy = new LinkedList<>();


    }

    @Override
    public void load() {
        while (scanner.hasNext()) {

            for (int j = 0; j < 15; j++) {
                a[j] = scanner.nextInt();
            }
            for (int j = 0; j < 15; j++) {
                P[j] = scanner.nextInt();
            }
            for (int j = 0; j < 15; j++) {
                IProcesy proces = new Procesy(a[j], P[j], q1);
                procesy.add(proces);
            }
        }
    }
    @Override
    public void sort(String method) {
        if (method == "FCFS") {
                for (int i = 0; i < 15; i++) {
                    while (!q1.settleProcess(procesy.get(i), i)) ;
                }
                q1.sort();
            }
         else if (method == "SJF") {
            for (int i = 0; i < 15; i++) {
                q1.sortSJF();
            }
        }

    }

    @Override
    public void answers(String method) {


            double[] tablica = q1.waitingTime();

            System.out.println( "sredni czas oczekiwania procesu metoda " + method + "= " + tablica[0]/15);
            System.out.println( "sredni czas przetwarzania procesu metoda" + method + "= " + tablica[1]/15);

    }


}

