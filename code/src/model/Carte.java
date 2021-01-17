package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class Carte implements Serializable {
    private int delai;
    private int valeur;

    public Carte(String n, String des, int del, int val, String img) {
        this.nom.set(n);
        description.set(des);
        delai = del;
        valeur = val;
        imageUrl.set(img);
        nomSer = n;
        descriptionSer = des;
        imageSer = img;
    }

    private final transient StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String name) { this.nom.set(name); }

    private String nomSer;
        public String getNomSer(){return nomSer;}

    private final transient StringProperty imageUrl = new SimpleStringProperty();
        public String getImageUrl() { return imageUrl.get(); }
        public void setImageUrl(String imageUrl) { this.imageUrl.set(imageUrl); }

    private String imageSer;
        public String getImageSer(){return imageSer;}

    private final transient StringProperty description = new SimpleStringProperty();
        public void setDescription(String name) { this.description.set(name); }

    private String descriptionSer;
        public String getDescriptionSer(){return descriptionSer;}


    public int getDelai() {
        return delai;
    }

    public int getValeur() { return valeur; }
    public void setDelai(int delai) { this.delai = delai; }
    public void setValeur(int valeur) { this.valeur = valeur; }

    public String getValAffichage() {
        return "+" + this.getValeur();
    }

    public void setAll(Carte c){
        setNom(c.getNomSer());
        setImageUrl(c.getImageSer());
        setDescription(c.getDescriptionSer());
        setValeur(c.getValeur());
        setDelai(c.getDelai());
    }

    @Override
    public String toString() {
        return "Carte{" +
                ", delai=" + delai +
                ", valeur=" + valeur +
                ", nom=" + nom +
                ", nomSer='" + nomSer + '\'' +
                ", imageUrl=" + imageUrl +
                ", imageSer='" + imageSer + '\'' +
                ", description=" + description +
                ", descriptionSer='" + descriptionSer + '\'' +
                '}';
    }
}
