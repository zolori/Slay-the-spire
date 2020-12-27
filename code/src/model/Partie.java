package model;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
    private int nbTour;
    private ArrayList<Salle> salles;
    private int nbSalle;
    private Joueur joueur;
    private int pdv = 100;
    private int degats = 100;

    public Partie(Joueur j) {
        joueur = j;
        salles = new ArrayList<Salle>(20);
        for (Salle s : salles) {
            pdv = (int)(pdv * 1.2);
            degats = (int)(degats * 1.2);
            s.nvMonstre("Monstre", pdv, s.getNumSalle(), degats);
        }
    }

    public int getNbSalle() {
        for (Salle s : salles) {
            nbSalle++;
        }
        return nbSalle;
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
