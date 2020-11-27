package model;

import java.util.ArrayList;

public class Joueur {
    private int numSalle;
    private ArrayList<Carte> deck;
    private int ptsAction;
    private String nom;
    private int pointsDeVie;
    private int pdvMax;

    public Joueur(String n, int pdv, int pa) {
        nom = n;
        pdvMax = pdv;
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = new ArrayList<>();
    }

    private void soin() { pointsDeVie = pdvMax; }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public int getPdv() {
        return pointsDeVie;
    }

    private String getNom() {
        return nom;
    }

    public int getPA() { return ptsAction; }

    public int getSalle() { return numSalle; }

    public void setSalle(int n) { numSalle = n; }

    public void renforcer (Bonus b) {
        switch (b) {
            case Degats -> pointsDeVie = pdvMax; // A voir comment renforcer les cartes
            case VieMax -> getPdv();
            case PointAction -> ptsAction++;
            case Regeneration -> soin();
        }
    }

}
