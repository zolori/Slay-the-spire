package model;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Joueur {
    private StringProperty nom;
    private int numSalle;
    private ArrayList<Carte> deck;
    private int ptsAction;
    private int pointsDeVie;
    private int pdvMax;
    private ArrayList<Carte> pioche;

    public Joueur(String n, int pdv, int pa) {
        setNom(n);
        pdvMax = pdv;
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = new ArrayList<>();
    }

    public StringProperty nomProperty() { return nom; }
    public void setNom(String nom) { this.nom.set(nom); }
    public String getNom() { return nom.get();}
    private void soin() { pointsDeVie = pdvMax; }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public int getPdv() { return pointsDeVie; }
    public int getPdvMax() { return pdvMax; }
    public int getPA() { return ptsAction; }
    public int getSalle() { return numSalle; }
    public void setSalle(int n) { numSalle = n; }
    public void setPdv(int pointsDeVie) { this.pointsDeVie = pointsDeVie; }
    public ArrayList<Carte> getPioche() { return pioche; }

    public void renforcer (Bonus b, Salle s) {
        //On récupère la pioche
        //ArrayList<Carte> cartes = s.getPioche();
        Boolean isBoss = false;

        //On regarde si il y avait un boss dans la salle
        if (this.numSalle == s.getNumSalle() && s.contientBoss()) //Si on tue un boss, bonus améliorés
        {
            isBoss = true; //Si il y a un boss, on passe la variable a true
            switch (b) {
                case Degats:
                    for ( Carte c : pioche ) {
                        deck = c.renforcement(deck, isBoss); //On utilise le renforcement de manière amélioré
                    }
                    break;
                case VieMax:
                    pdvMax=getPdvMax()+10; //On augmente le maximum de pdv
                    break;
                case PointAction:
                    ptsAction=ptsAction+2; //On augmente les ptsAction de 2
                    break;
                case Regeneration:
                    pdvMax=getPdvMax()+5; //On augmente le maximum de pdv
                    soin(); // On remet le joueur au maximum de ses pts de vie
                    break;
            }
        }
        else {
            switch (b) {
                //Bonus simples
                case Degats:
                    for ( Carte c : pioche ) {
                        deck = c.renforcement(deck, isBoss); //On utilise le renforcement de manière amélioré
                    }
                    break;
                case VieMax:
                    pdvMax = getPdvMax() + 5; //On augmente le maximum de pdv
                    break;
                case PointAction:
                    ptsAction++; //On augmente les ptsAction de 1
                    break;
                case Regeneration:
                    soin(); // On remet le joueur au maximum de ses pts de vie
                    break;
            }
        }
    }

    public void utiliserCarte(Carte c, Monstre cible){
        if(c.getPA() < getPA()){
            //c.jouerCarte(cible);
        }
        for (Carte carte: deck) {
            if(carte.getId() == c.getId())
                deck.remove(carte);
                //pioche
        }
        return;
    }

}
