package model;

import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;

public class Manager {
    private Partie partie;

    private Manager(){}

    private static Manager INSTANCE = new Manager();

    public static  Manager getInstance() {
        return INSTANCE;
    }

    public Joueur createJoueur(String nom, int pdv) {
        Joueur joueur = new Joueur(nom, pdv, 3);
        partie = new Partie(joueur);

        return joueur;
    }

    public Partie getPartie() {
        return partie;
    }

}
