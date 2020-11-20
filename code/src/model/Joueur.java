package model;

import java.util.ArrayList;

public class Joueur extends Entite{
    private int numSalle;
    private ArrayList<Carte> deck;

    public Joueur(String p, int pdv) {
        super(p, pdv);
        numSalle = 0;
        deck = new ArrayList<>();
    }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public String toString(){
        return super.toString() + numSalle/*+ deck*/;
    }
}
