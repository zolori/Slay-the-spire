package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Carte {
    private StringProperty nom;
    public StringProperty nomProperty() { return nom; }
    public void setNom(String nom) {this.nom.set(nom);}

    private StringProperty description;
    public StringProperty descriptionProperty() { return description; }
    public void setDescription(String description) { this.description.set(description);}

    private IntegerProperty pointAction;
    public int getPointAction() { return pointAction.get(); }
    public IntegerProperty pointActionProperty() { return pointAction;}
    public void setPointAction(int pointAction) { this.pointAction.set(pointAction); }

    private int delai;
    private int valeur;
    private Effets effet;
    private boolean isUsed=false;

    public Carte(String n, String des, int del, int val,Effets e, int pa) {
        setNom(n);
        setDescription(des);
        delai = del;
        valeur = val;
        effet = e;
        setPointAction(pa);
    }

    public int getDelai() {
        return delai;
    }

    public int getValeur() { return valeur; }

    public boolean getUsed(){ return isUsed;}

    public void setUsed(boolean used) { isUsed = used; }

    public Effets getEffet() {
        return effet;
    }

    public void renforcement(Boolean isBoss) {
        if(isBoss){
            valeur=valeur+2;
        }
        else{
            valeur=valeur+1;
        }

    }

    public void jouerCarte(Monstre cible){
        //Quand on joue une carte, on sélectionne une cible que l'on renvoie en paramètre
        cible.setPdv(cible.getPdv()-getValeur()); //On réduit les points de vie du Monstre
        setUsed(true); //On définit la carte en tant qu'utilisée
    }
}
