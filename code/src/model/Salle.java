package model;

import java.util.ArrayList;

public class Salle {
    private int num;
    private Joueur joueur;
    private static int numSalle;
    private static ArrayList<Monstre> listeMonstre;
    private static int nbMonstre = 0;
    private int nbTour;

    public Salle(int numeroSalle) {
        numSalle = numeroSalle;
        listeMonstre = new ArrayList<Monstre>();
        if(getNumSalle()%5 == 0) {
            nvMonstre("Boss", 200, getNumSalle(), 20);
        }
        else
            nvMonstre("Orc", 100, getNumSalle(), 10);
    }

    public static ArrayList<Monstre> getListeMonstre() {
        return listeMonstre;
    }
    public static void setListeMonstre(ArrayList<Monstre> Monstres) {
        Salle.listeMonstre = Monstres;
    }

    public static void nvMonstre(String n, int pdv, int salle, int degats){
        nbMonstre += 1;
        listeMonstre.add(new Monstre(n, pdv, salle, degats));
    }

    public static int getNumSalle() {
        return numSalle;
    }
    public void getNbMonstre(){
        nbMonstre = listeMonstre.size();
    }
    public int getNbTour() { return nbTour; }

    public boolean contientBoss() {
        boolean oui = false;
        for (Monstre m : listeMonstre ) {
            if (m.isBoss())
                oui = true;
        }
        return oui;
    }
}
