package model;

import java.util.ArrayList;

public class Salle {
    private int num;
    private ArrayList<Carte> pioche;
    private Joueur joueur;
    private ArrayList<Monstre>  monstres;
    int nbMonstre;
    private Boolean clean = true;
    private Bonus typeBonus;

    public Salle(Joueur j, int n) {
        joueur = j;
        num = n;
        pioche = new ArrayList<>();
        monstres = new ArrayList<>();
    }

    public int getNum() { return num; }

    public ArrayList<Monstre> getMonstres() { return monstres; }

    public void setMonstre(Monstre m) { monstres.add(m); }

    public int getNbMonstre() {
        for ( Monstre m : monstres ) {
            nbMonstre++;
        }
        return nbMonstre;
    }

    private Boolean isClean() { return clean; }

    public void Victoire() {
        for ( Monstre m : monstres ) {
            if(m.getPdv() != 0)
                clean = false;
        }
        if(isClean())
            changerSalle(typeBonus);
    }

    public void changerSalle(Bonus b) {
        num++;
        new Salle(joueur, num);
        joueur.setSalle(num);
        joueur.renforcer(b);
    }

    public void setBonus(Bonus b) { typeBonus = b; }
}
