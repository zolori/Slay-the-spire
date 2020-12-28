package model;

import java.io.IOException;
import java.lang.Thread;

public class Manager {
    private Partie partie;

    private Manager(){}

    private static Manager INSTANCE = new Manager();

    public static  Manager getInstance() {
        return INSTANCE;
    }

    public Joueur createJoueur(String nom, int pdv, int pa) {
        Joueur joueur = new Joueur(nom, pdv,pa, 3);
        partie = new Partie(joueur);

        return joueur;
    }

    public Partie getPartie() {
        return partie;
    }
}
