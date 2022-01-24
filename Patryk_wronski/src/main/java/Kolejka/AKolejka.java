package Kolejka;

import Procesy.IProcesy;

import java.util.ArrayList;
import java.util.List;

public abstract class AKolejka implements IKolejka{
    public final int x;
    IProcesy[] procesy1;
    IProcesy[] procesy2;
    List<IProcesy> kolejka;

    public AKolejka(int x) {

        this.x = x;
        procesy1 = new IProcesy[x];
        procesy2 = new IProcesy[x];
        kolejka = new ArrayList<>();

    }

    @Override
    public double getProcessA(int x) {
        return procesy1[x].getA();
    }

    @Override
    public double getProcessP(int x) {

        return procesy1[x].getP();
    }

    @Override
    public void sort() {
        for (int i = 0; i < procesy1.length; i++) {
            for (int j = 0; j < procesy1.length - 1; j++) {
                if (getProcessA(j+1) < getProcessA(j)) {
                    IProcesy[] proc = new IProcesy[procesy1.length];
                    proc[j] = procesy1[j];
                    procesy1[j] = procesy1[j + 1];
                    procesy1[j + 1] = proc[j];
                } else if (getProcessA(j+1) == getProcessA(j)  && getProcessP(j+1)  < getProcessP(j))
                {
                    IProcesy[] proc = new IProcesy[procesy1.length];
                    proc[j] = procesy1[j];
                    procesy1[j] = procesy1[j + 1];
                    procesy1[j + 1] = proc[j];
                }
            }
        }
    }
    @Override
    public void whoIsNextSJF(int i, double t){
        if (procesy2[i] != null) {
            if (procesy2[i].getA() > t) {
                kolejka.add(procesy2[i]);
                procesy2[i] = null;
            } else {
                for (int j = i; j < procesy2.length; j++) {
                    if (procesy2[j] != null) {
                        if (procesy2[j].getA() <= t) {
                            kolejka.add(procesy2[j]);
                            procesy2[j] = null;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void sortKolejka() {
        for (int i = 0; i < kolejka.size(); i++) {
            for (int j = 0; j < kolejka.size(); j++) {
                if (kolejka.get(i).getP() > kolejka.get(j).getP()) {
                    IProcesy[] proc = new IProcesy[kolejka.size()];
                    proc[j] = kolejka.get(i);
                    kolejka.set(i, kolejka.get(j));
                    kolejka.set(j, proc[j]);
                }
            }
        }
    }
    @Override
    public void sortSJF(){
        for(int i = 0; i<procesy1.length; i++) {
            procesy2[i] = procesy1[i];
        }
        double t = getProcessA(0)+getProcessP(0);
        for (int i =1; i< procesy1.length; i++){
            whoIsNextSJF(i,t);
            sortKolejka();
            procesy1[i]= kolejka.get(kolejka.size()-1);
            if (getProcessA(i) > t) {
                t = getProcessP(i) + getProcessA(i);
            } else {
                t += getProcessP(i);
            }
            kolejka.remove(kolejka.size() - 1);
        }
    }


    @Override
    public int getProcessPosition(IProcesy process) {
        Integer processX = null;
        for (int i = 0; i < procesy1.length; i++) {
            if (process == procesy1[i]) {
                processX = i;
            }

        }
        if (processX == null) {
            return -1;
        } else {
            return processX.intValue();
        }
    }

    @Override
    public boolean settleProcess(IProcesy process, int x) {

        int settled = getProcessPosition(process);

        if (procesy1[x] != null) {
            return false;
        }
        if (settled >= 0) {
            procesy1[settled] = null;
        }
        procesy1[x] = process;
        return true;

    }



    @Override
    public String toString() {
        StringBuffer buffor = new StringBuffer();
        {
            for (int i = 0; i < x; i++) {
                if (procesy1[i] == null) {
                    buffor.append("[  ]");
                } else {
                    buffor.append(procesy1[i].getA());
                }

            }
            buffor.append("\n");

            return buffor.toString();
        }
    }

    @Override
    public double[] waitingTime() {
        double a = 0;
        double P = getProcessP(0);
        double t = getProcessA(0) + getProcessP(0);
        for (int i = 1; i < procesy1.length; i++) {
            double a2;
            a2 = getProcessA(i);
            double P2;
            P2 = getProcessP(i);
            if (t >= a2) {
                a += t - a2;
                P = P+ P2+t-a2;
                t += P2;


            } else if (t < a2) {
                t = a2 + P2;
                P += P2;
            }
        }
        double tab[] = new double[2];
        tab[0] = a;
        tab[1] = P;
        return tab;
    }
}
