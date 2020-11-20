package model;

import java.util.ArrayList;

public class Entite {
    private String nom;
    private int pointsDeVie;

    public Entite(String n, int pdv) {
        nom = n;
        pointsDeVie = pdv;
    }
    private int getPdv() {
        return pointsDeVie;
    }
    private String getNom() {
        return nom;
    }

    public String toString(){
        return nom + pointsDeVie;
    }

}
