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
        //Si le numéro du tour correspond a celui de la super attaque, on lance cette dernière
        if (s.getNbTour()%temps == 0) {
            j.setPdv(j.getPdv() - Attaqueforte);
        }
        else{
            attaque(j);
        }
    }

}
