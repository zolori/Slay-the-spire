package model;

public class Entite {
    private final String nom;
    private int pointsDeVie;
    private boolean isAlive=true;

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

    public boolean isDead(){
        if(isAlive)
            return false;
        return true;
    }

    public void attaque(Entite e,int degats){
        isAlive=!e.getAttacked(degats);
    }

    public boolean getAttacked(int degats){
        pointsDeVie=pointsDeVie-degats;
        return isDead();
    }

}
