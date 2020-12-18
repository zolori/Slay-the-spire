package model;

import java.util.ArrayList;

public class Monstre {
    private int numSalle;
    private int degats;
    private String nom;
    private int pointsDeVie;
    private ArrayList<Carte> deck;
    private static ArrayList<Monstre> ensembleMonstres;

    public Monstre(String n, int pdv, int num, int d) {
        nom = n;
        pointsDeVie = pdv;
        numSalle = num;
        degats = d;
    }

    public int getNalle() {
        return numSalle;
    }

    public int getPdv() {
        return pointsDeVie;
    }

    public void setPdv(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    private String getNom() {
        return nom;
    }

    private int getDegats() {return degats;}

    protected boolean isBoss() {
        if (this.getClass().getName()=="Boss"){
            return true;
        }
        return false;
    }

    public void attaque(Joueur j){
        j.setPdv(j.getPdv() - degats);
    }
}
