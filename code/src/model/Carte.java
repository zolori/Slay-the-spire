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
    private String valAffichage;
    private Effets effet;

    public Carte(String n, String des, int del, int val, Effets e, int pa, String img) {
        id = UUID.randomUUID().toString();
        this.nom.set(n);
        description.set(des);
        delai = del;
        valeur = val;
        effet = e;
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

    public int getDelai() {
        return delai;
    }
    public int getValeur() { return valeur; }

    public String getValAffichage() {
        valAffichage = "+" + this.getValeur();
        return valAffichage;
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
