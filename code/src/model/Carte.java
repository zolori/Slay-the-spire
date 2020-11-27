package model;

public class Carte {
    private String nom;
    private String description;
    private int delai;
    private int valeur;
    private Effets effet;
    private int pointAction;
    private boolean isUsed=false;

    public Carte(String n, String des, int del, int val,Effets e, int pa) {
        nom = n;
        description = des;
        delai = del;
        valeur = val;
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

    public int getValeur() { return valeur; }

    public boolean getUsed(){ return isUsed;}

    public void setUsed(boolean used) { isUsed = used; }

    public Effets getEffet() {
        return effet;
    }

    public int getPA() { return pointAction; }

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
