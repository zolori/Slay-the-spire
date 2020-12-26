package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Monstre {
    private int numSalle;
    private int degats;
    private String nom;

    private IntegerProperty pointsDeVie= new SimpleIntegerProperty();
        public int getPointsDeVie() { return pointsDeVie.get(); }
        public IntegerProperty pointsDeVieProperty() { return pointsDeVie; }
        public void setPointsDeVie(int pointsDeVie) { this.pointsDeVie.set(pointsDeVie); }

    private ArrayList<Carte> deck;
    private static ArrayList<Monstre> ensembleMonstres;
    private String image;

    public Monstre(String n, int pdv, int num, int d) {
        nom = n;
        setPointsDeVie(pdv);
        numSalle = num;
        degats = d;
        image="/images/Orcs.jpg";
    }

    public int getSalle() {
        return numSalle;
    }
    public int getDegats() { return degats; }
    public  String getNom() {
        return nom;
    }

    protected boolean isBoss() {
        if (this.getClass().getName() == "Boss"){
            return true;
        }
        return false;
    }

    public void attaque(Joueur j){
        j.setPointsDeVie(j.getPointsDeVie() - degats);
    }

    public String getImage() { return image; }

    public void subit(int valeur) {
        setPointsDeVie(getPointsDeVie()-valeur);
    }
}
