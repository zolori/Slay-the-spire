package model;

public class Carte {
    private String nom;
    private String description;
    private int delai;
    private Effets effet;
    private int pointAction;

    public Carte(String n, String des, int del, Effets e, int pa) {
        nom = n;
        description = des;
        delai = del;
        effet = e;
        pointAction = pa;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getDelai() {
        return delai;
    }

    public Effets getEffet() {
        return effet;
    }

    public int getPA() { return pointAction; }

    // A voir comment ameliorer les cartes
    public void renforcement() {
        effet = effet;
    }
}
