package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Carte implements Serializable {
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
        nomSer=n;
        descriptionSer=des;
        imageSer=img;
    }

    private final transient StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty nomProperty() { return nom; }
        public void setNom(String name) { this.nom.set(name); }
    private String nomSer;
        public void setNomSer(String nom){nomSer=nom; setNom(nom);}
        public String getNomSer(){return nomSer;}


    private final transient StringProperty imageUrl = new SimpleStringProperty();
        public String getImageUrl() { return imageUrl.get(); }
        public StringProperty imageUrlProperty() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl.set(imageUrl); }
    private String imageSer;
        public void setimageSer(String image){imageSer=image; setImageUrl(image);}
        public String getImageSer(){return imageSer;}

    private final transient StringProperty description = new SimpleStringProperty();
        public String getDescription() { return description.get(); }
        public StringProperty descriptionProperty() { return description; }
        public void setDescription(String name) { this.description.set(name); }
    private String descriptionSer;
        public void setdescriptionSer(String desc){descriptionSer=desc; setDescription(desc);}
        public String getDescriptionSer(){return descriptionSer;}


    public int getDelai() {
        return delai;
    }
    public int getValeur() { return valeur; }
    public Effets getEffet() { return effet; }

    public void setDelai(int delai) { this.delai = delai; }
    public void setValeur(int valeur) { this.valeur = valeur; }
    public void setEffet(Effets effet) { this.effet = effet; }

    public String getValAffichage() {
        valAffichage = "+" + this.getValeur();
        return valAffichage;
    }

    public void setAll(Carte c){
            setNom(c.getNomSer());
            setImageUrl(c.getImageSer());
            setDescription(c.getDescriptionSer());
            setValeur(c.getValeur());
            setDelai(c.getDelai());
            setEffet(c.getEffet());
    }

    @Override
    public String toString() {
        return "Carte{" +
                ", delai=" + delai +
                ", valeur=" + valeur +
                ", effet=" + effet +
                ", nom=" + nom +
                ", nomSer='" + nomSer + '\'' +
                ", imageUrl=" + imageUrl +
                ", imageSer='" + imageSer + '\'' +
                ", description=" + description +
                ", descriptionSer='" + descriptionSer + '\'' +
                '}';
    }
}
