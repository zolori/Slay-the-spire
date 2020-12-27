package model;

import java.io.IOException;
import java.lang.Thread;

public class Manager {
    private static Salle salle;
    private Joueur joueur;
    private Partie partie;

    private Manager(){}

    private static Manager INSTANCE = new Manager();

    public static  Manager getInstance() {
        return INSTANCE;
    }

    public Joueur getJoueur() { return joueur; }
    public Joueur createJoueur(String nom, int pdv, int pa) {
        joueur = new Joueur(nom, pdv,pa, 3);
        joueur.setSalle(1);

        return joueur;
    }

    public Salle getSalle() { return salle; }
    public void createSalle(int numsalle){
        salle = new Salle(numsalle);
    }

    public Partie getPartie() {
        return partie;
    }
}
