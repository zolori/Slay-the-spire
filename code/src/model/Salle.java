package model;

import java.util.ArrayList;

public class Salle {
    private int numSalle;
    private static Monstre monstre;
    private static int nbMonstre = 0;
    private int nbTour;

    public Salle(int numeroSalle) {
        numSalle = numeroSalle;
        monstre = new Monstre("Orcs",100,numSalle,10);
        if(getNumSalle()%5 == 0) {
            nvMonstre("Boss", 200, getNumSalle(), 20);
        }
        else
            nvMonstre("Orc", 100, getNumSalle(), 10);
    }

    public Monstre getMonstre() {
        return monstre;
    }
    public  void setMonstre(Monstre Monstres) {
        this.monstre = Monstres;
    }

    public void nvMonstre(String n, int pdv, int salle, int degats){
        nbMonstre += 1;
        this.monstre = new Monstre(n, pdv, salle, degats);
    }

    public int getNumSalle() {
        return numSalle;
    }
    public int getNbTour() { return nbTour; }

    public boolean contientBoss() {
        boolean oui = false;
        if (monstre.isBoss())
                oui = true ;
        return oui;
    }

}
