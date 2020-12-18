package model;

import java.util.ArrayList;

//CRUD : Create read update delete
public class Manager {
    private int salleActuelle;
    private ArrayList<Salle> salles;
    private Joueur joueur;

    public Manager(){
        salles = new ArrayList<Salle>();
    }

    public ArrayList<Salle> getSalles() { return salles;  }

    public void setJoueur(Joueur joueur) {this.joueur = joueur;}

    public void setSalleActuelle(int salleActuelle) {this.salleActuelle = salleActuelle;}

}
