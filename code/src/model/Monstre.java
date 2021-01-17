package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;
import java.util.ArrayList;

public class Monstre implements Personnage, Serializable {
    private int numSalle;
    private int degats;
    private String nom;

    private transient IntegerProperty pointsDeVie= new SimpleIntegerProperty();
        public int getPointsDeVie() { return pointsDeVie.get(); }
        public IntegerProperty pointsDeVieProperty() { return pointsDeVie; }
        public void setPointsDeVie(int pointsDeVie) { this.pointsDeVie.set(pointsDeVie); pdv=getPointsDeVie();}

    private int pdv;
    public int getPdv() { return pdv; }
    public void setPdv(int pdv){this.pdv=pdv;}



    private String image;

    public Monstre(String n, int pdv, int num, int d) {
        nom = n;
        setPointsDeVie(pdv);
        numSalle = num;
        degats = d;
        image = "/images/Orcs.jpg";
    }

    public int getSalle() {
        return numSalle;
    }
    public int getDegats() { return degats; }
    public  String getNom() {
        return nom;
    }
    public String getImage() { return image; }

    public void setNumSalle(int numSalle) { this.numSalle = numSalle; }
    public void setDegats(int degats) { this.degats = degats; }
    public void setNom(String nom) { this.nom = nom; }

    protected boolean isBoss() {
        if (this.getClass().getName() == "Boss"){
            return true;
        }
        return false;
    }

    @Override
    public boolean attaque(Personnage p, int val) {
        p.setPointsDeVie(p.getPointsDeVie() - degats);
        return p.getPointsDeVie() > 0;
    }

    public void setAll(Monstre m) {
        setNom(m.getNom());
        setPointsDeVie(m.getPdv());
        setNumSalle(m.getSalle());
        setDegats(m.getDegats());
    }
}
