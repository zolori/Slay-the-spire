package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.UUID;

public class Carte {
    private String id;
    private String description;
    private int delai;
    private int valeur;
    private Effets effet;
    private int ptsAction;

    public Carte(String n, String des, int del, int val, Effets e, int pa) {
        id = UUID.randomUUID().toString();
        this.nom.set(n);
        description = des;
        delai = del;
        valeur = val;
        effet = e;
        ptsAction = pa;
    }
    private StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty NomProperty() { return nom; }
        public void setNom(String NomCarte) { this.nom.set(String.valueOf(nom)); }

    public String getId() { return id; }
    public String getDescription() {
        return description;
    }
    public int getDelai() {
        return delai;
    }
    public int getValeur() { return valeur; }
    public Effets getEffet() { return effet; }
    public int getPA() { return ptsAction; }

    public ArrayList<Carte> renforcement(ArrayList<Carte> deck, Boolean isboss) {
        if(isboss)
            for ( Carte c : deck ) { c.valeur += 6; }
        else
            for ( Carte c : deck ) { c.valeur += 3; }

        return deck;
    }

}
