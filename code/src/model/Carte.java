package model;

public class Carte {
    private String nom;
    private String description;
    private int delai;
    private Effets effet;

    public Carte(String n, String des, int del, Effets e) {
        nom = n;
        description = des;
        delai = del;
        effet = e;
    }

    private String getNom() {
        return nom;
    }

    private String getDescription() {
        return description;
    }

    private int getDelai() {
        return delai;
    }

    private Effets getEffet() {
        return effet;
    }

    /*private Boolean isPlayed () {
        if (Joueur.use(this)){

        }
    }*/
}
