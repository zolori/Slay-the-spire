package model;

import java.util.ArrayList;

public class Partie {
    private int nbTour;
    private boolean isOver;
    private ArrayList<Salle> salles;
    private ArrayList<Carte> cartes;
    private Joueur joueur;
    private int nbSalle;

    public Partie() {
        salles = new ArrayList<Salle>();
        joueur = new Joueur("Kevin", 100, 1);
        joueur.setSalle(1);
        salles = new ArrayList<Salle>();
    }

    public int getNbSalle() {
        for (Salle s : salles) {
            nbSalle++;
        }
        return nbSalle;
    }
    public ArrayList<Salle> getSalles() { return salles; }
    public void addSalle(Salle s) { salles.add(s); }
    public Joueur getJoueur(Joueur joueur) { return joueur; }


}
