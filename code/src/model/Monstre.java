package model;

import java.util.ArrayList;

public class Monstre extends Entite{
    private String nom;
    private int pointsDeVie;
    private int numSalle;
    private ArrayList<Carte> deck;

    public Monstre(String n, int pdv, int num) {
        super(n, pdv);
        numSalle = num;
        deck = new ArrayList<>();
    }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public String toString(){
        return nom + pointsDeVie;
    }
}
