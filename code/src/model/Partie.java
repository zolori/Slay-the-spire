package model;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
    private int nbTour;
    private boolean isOver;
    private ArrayList<Salle> salles;
    private int nbSalle;
    private Manager leManager;

    public Partie() {
        salles = new ArrayList<Salle>();
        leManager = new Manager(initCarte());
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

    private ArrayList initCarte() {
        int i = 0;
        Random rand = new Random();
        ArrayList<Carte> init = new ArrayList<>();

        Carte cb = new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png");
        Carte cp = new Carte("Protection", "Description carte", 1, 30, Effets.physique, 1, "images/bouclier.png");
        Carte cs = new Carte("Soin", "Description carte", 1, 40, Effets.physique, 1, "images/coeur.png");

        while(i < 20) {
            int n = rand.nextInt(2);
            if(n == 0) init.add(cb);
            if(n == 1) init.add(cp);
            if(n == 2) init.add(cs);
        }
        return init;
    }
}
