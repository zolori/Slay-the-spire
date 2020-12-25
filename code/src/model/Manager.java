package model;

import java.util.ArrayList;

//CRUD : Create read update delete
public class Manager {
    private int salleActuelle;
    private ArrayList<Carte> pioche;
    private Joueur joueur;

    public Manager(ArrayList<Carte> c){
        setSalleActuelle(1);
//        vc = new VueCarte((ObservableList<Carte>) c);
        pioche = c;
        joueur = new Joueur("Kevin", 100, 1, c);
        joueur.setSalle(1);
    }
    public void setSalleActuelle(int salleActuelle) { this.salleActuelle = salleActuelle; }
//    public static Joueur getJoueur(){ return joueur; }
}
