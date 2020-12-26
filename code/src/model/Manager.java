package model;

import java.util.ArrayList;
import java.util.Random;

//CRUD : Create read update delete
public class Manager {
    private int salleActuelle;
    private Joueur joueur;

    public Manager(){
        setSalleActuelle(1);
    }
    public void setSalleActuelle(int salleActuelle) { this.salleActuelle = salleActuelle; }
    //public static Joueur getJoueur(){ return joueur; }

    public Joueur createJoueur(String nom, int pdv, int pa) {
        Joueur j = new Joueur(nom, pdv, pa, 3);
        j.setSalle(1);

        return j;
    }
}
