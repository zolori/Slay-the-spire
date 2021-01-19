package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Carte est la classe représentant une carte placé dans le deck du joueur et pouvant être séléctionné par celui-ci
 */
public class Carte {
    /**
     * Nombre de tour avant que l'effet prenne fin.
     */
    private int delai;

    /**
     * Montant de l'effet.
     *      (ex : 35 dégats d'attaque, 45 de bouclier...)
     */
    private int valeur;

    /**
     *
     * @param n
     *      Nom de la carte.
     * @param des
     *      Description des effets de la carte.
     * @param del
     *      délai de l'effet.
     * @param val
     *      valeur de l'effet.
     * @param img
     *      Image de la carte.
     */
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

    /**
     * Propriété nom.
     */
    private final transient StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String name) { this.nom.set(name); }

    /**
     * Nom pour la serialisation
     */
    private String nomSer;
        public String getNomSer(){return nomSer;}

    /**
     * Propriété de l'image.
     */
    private final transient StringProperty imageUrl = new SimpleStringProperty();
        public String getImageUrl() { return imageUrl.get(); }
        public void setImageUrl(String imageUrl) { this.imageUrl.set(imageUrl); }

    /**
     * Image pour la serialisation
     */
    private String imageSer;
        public String getImageSer(){return imageSer;}

    /**
     * Propriété description.
     */
    private final transient StringProperty description = new SimpleStringProperty();
        public void setDescription(String name) { this.description.set(name); }

    /**
     * Description pour la serialisation
     */
    private String descriptionSer;
        public String getDescriptionSer(){return descriptionSer;}

    /**
     * @return le délai de l'effet
     */
    public int getDelai() {
        return delai;
    }

    /**
     * @return la valeur de l'effet.
     */
    public int getValeur() { return valeur; }

    /**
     * Met à jour le délai de la carte.
     *
     * @param delai
     *      Le nouveau délai de l'effet.
     */
    public void setDelai(int delai) { this.delai = delai; }

    /**
     * Met à jour la valeur de l'effet.
     *
     * @param valeur
     *      La nouvelle valeur de l'effet.
     */
    public void setValeur(int valeur) { this.valeur = valeur; }

    /**
     * Converti la valeur en chaine de caractère avec le signe "+" devant.
     *
     * @return une chaine de caractère pour l'affichage de l'effet sur la carte.
     *      (ex: "+45" dégats d'attaque)
     */
    public String getValAffichage() {
        return "+" + this.getValeur();
    }

    /**
     * Sert lors du chargement d'une précédente sauvegarde.
     * Remet en place les valeurs des cartes sauvegardées.
     *
     * @param c
     *      Carte sauvegardée.
     */
    public void setAll(Carte c){
        setNom(c.getNomSer());
        setImageUrl(c.getImageSer());
        setDescription(c.getDescriptionSer());
        setValeur(c.getValeur());
        setDelai(c.getDelai());
    }
}
