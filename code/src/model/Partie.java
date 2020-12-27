package model;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
    private int nbTour;
    private ArrayList<Salle> salles;
    private final int NBSALLE = 21;
    private Joueur joueur;
    private int pdv = 100;
    private int degats = 100;
    int i = 1;

    public Partie(Joueur j) {
        joueur = j;
        salles = new ArrayList<Salle>(NBSALLE-1);
        for (Salle s : salles) {
            s = new Salle(i);
            pdv = (int)(pdv * 1.2);
            degats = (int)(degats * 1.2);
            s.nvMonstre("Monstre", pdv, s.getNumSalle(), degats);
            i++;
        }
    }

    public int getNbSalle() {
        return NBSALLE;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<Salle> getSalles() { return salles; }
    public void addSalle(Salle s) { salles.add(s); }

    public void finPartie() {
        // mettre fin à la partie
    }
}
