package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;
import java.util.ArrayList;

public class Monstre implements Personnage, Serializable {
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

    public void serialisation(ObjectOutputStream oos) {
        try {
            oos.writeObject(getNom());
            oos.writeObject(getPointsDeVie());
            oos.writeObject(getSalle());
            oos.writeObject(getDegats());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deserialisation(ObjectInputStream ois) {
        try {
            nom= (String) ois.readObject();
            setPointsDeVie((int) ois.readObject());
            numSalle=(int) ois.readObject();
            degats= (int) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
