package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Carte implements Serializable,SerialisationPartie {
    private String id;
    private int delai;
    private int valeur;
    private Effets effet;
    private int ptsAction;

    public Carte(String n, String des, int del, int val, Effets e, int pa, String img) {
        id = UUID.randomUUID().toString();
        this.nom.set(n);
        description.set(des);
        delai = del;
        valeur = val;
        effet = e;
        ptsAction = pa;
        imageUrl.set(img);
    }

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty nomProperty() { return nom; }
        public void setNom(String name) { this.nom.set(name); }

    private final StringProperty imageUrl = new SimpleStringProperty();
        public String getImageUrl() { return imageUrl.get(); }
        public StringProperty imageUrlProperty() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl.set(imageUrl); }

    private final StringProperty description = new SimpleStringProperty();
        public String getDescription() { return description.get(); }
        public StringProperty descriptionProperty() { return description; }
        public void setDescription(String name) { this.description.set(name); }

    public String getId() { return id; }
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


    public void serialisation(ObjectOutputStream oos) {
        try {
            oos.writeObject(getNom());
            oos.writeObject(getImageUrl());
            oos.writeObject(getDescription());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deserialisation(ObjectInputStream ois) {
        try {
            setNom((String) ois.readObject());
            setImageUrl((String) ois.readObject());
            setDescription((String) ois.readObject());
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
