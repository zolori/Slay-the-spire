package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partie implements Serializable {
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
            if(i%5!=0) {
                pdv = (int) (pdv * 1.5);
                degats = (int) (degats * 1.5);
                Monstre m = new Monstre("Monstre", pdv, i, degats);
                s.setMonstre(m);

            }else{
                pdv = (int) (pdv * 2);
                degats = (int) (degats * 2);
                Boss b = new Boss("Boss", pdv, i, degats);
                s.setMonstre(b);
            }
            salles.add(s);
        }
    }

    public Partie(Joueur j, ArrayList<Carte> deck, Salle s, Monstre m) {
        joueur=j;
        j.setDeck(deck);
        s.setMonstre(m);
        salles.add(s);
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

    public void setSalle(Salle s) { salles.add(s); }
}
