package model;

import java.util.ArrayList;

public class Boss extends Monstre{
    private ArrayList<Monstre> allies;
    private int Attaqueforte;
    private int temps;

    public Boss(String n, int pdv, int num, int d) {
        super(n, pdv, num, d);
        allies = new ArrayList<>();
    }

    public void superAttaque(Salle s, Joueur j) {
        if (s.getNbTour()%temps == 0) {
            super.attaque(Attaqueforte);
        }
    }

}
