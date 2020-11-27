package model;

import java.util.ArrayList;

public class Partie {
    private int nbTour;
    private boolean isOver;
    private ArrayList<Salle> salles;
    private int nbSalle;

    public Partie() {
        salles = new ArrayList<Salle>();
    }

    public int getNbSalle() {
        for (Salle s : salles) {
            nbSalle++;
        }
        return nbSalle;
    }

    public void addSalle(Salle s) {
        salles.add(s);
    }
}
