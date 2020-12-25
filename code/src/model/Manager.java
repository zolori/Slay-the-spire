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

    private ArrayList initPioche() {
        int i = 0;
        Random rand = new Random();
        ArrayList<Carte> pioche = new ArrayList<>();

        Carte cb = new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png");
        Carte cp = new Carte("Protection", "Description carte", 1, 30, Effets.physique, 1, "images/bouclier.png");
        Carte cs = new Carte("Soin", "Description carte", 1, 40, Effets.physique, 1, "images/coeur.png");

        while(i < 20) {
            int n = rand.nextInt(2);
            if(n == 0) pioche.add(cb);
            if(n == 1) pioche.add(cp);
            if(n == 2) pioche.add(cs);
            i++;
        }
        return pioche;
    }

    public Joueur createJoueur(String nom, int pdv, int pa) {
        Joueur j = new Joueur(nom, pdv, pa, initPioche());
        j.setSalle(1);

        return j;
    }
}
