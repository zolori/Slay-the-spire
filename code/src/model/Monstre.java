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

    public int getSalle() {
        return numSalle;
    }
    public int getPdv() { return pointsDeVie; }
    public int getDegats() { return degats; }
    public  String getNom() {
        return nom;
    }

    public void setPdv(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    protected boolean isBoss() {
        if (this.getClass().getName() == "Boss"){
            return true;
        }
        return false;
    }

    public void attaque(Joueur j){
        j.setPdv(j.getPdv() - degats);
    }
}
