package model;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
    private int nbTour;
    private ArrayList<Salle> salles;
    private final int NBSALLE = 21;
    private Joueur joueur;
    private int pdv = 100;
    private int degats = 10;
    int i = 1;

    public Partie(Joueur j) {
        joueur = j;
        joueur.setNumSalle(1);
        salles = new ArrayList<Salle>(NBSALLE-1);

        for (int i = 1; i < NBSALLE; i++) {
            Salle s = new Salle(i);
            pdv = (int)(pdv * 1.5);
            degats = (int)(degats * 1.5);
            Monstre m = new Monstre("Monstre", pdv, i, degats);
            s.setMonstre(m);
            salles.add(s);
        }
    }

    public Salle getSalle(int num) {
        return salles.get(num-1);
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
