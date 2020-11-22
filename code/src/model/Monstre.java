package model;

import java.util.ArrayList;

public class Monstre extends Entite{
    private String nom;
    private int pointsDeVie;
    private Salle Terrain;
    private ArrayList<Carte> deck;
    private static ArrayList<Monstre> ensembleMonstres;

    public Monstre(String n, int pdv, Salle salle) {
        super(n, pdv);
        Terrain= salle;
        deck = new ArrayList<>();
        ensembleMonstres.add(this);
        Terrain.setListeMonstre(ensembleMonstres);
    }


    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public int getNumSalle() {
        return Terrain.getNumSalle();
    }

    public String toString(){
        return nom + pointsDeVie;
    }

    public ArrayList<Monstre> getEnsembleMonstres(){ return ensembleMonstres;}
}
