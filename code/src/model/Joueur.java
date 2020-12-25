package model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Joueur {
    private int numSalle;
    private ArrayList<Carte> deck;
    private int ptsAction;
    private int pointsDeVie;
    private int pdvMax;
    private ArrayList<Carte> pioche;

    public Joueur(String n, int pdv, int pa, ArrayList<Carte> p) {
        setNom(n);
        pdvMax = pdv;
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = new ArrayList<>();
        pioche = p;
        initDeck();
    }

    private StringProperty nom = new SimpleStringProperty();
    public String getNom() { return nomProperty().get(); }
    public void setNom(String value) { nomProperty().set(value); }
    public StringProperty nomProperty() { return this.nom; }

    public int getPdv() { return pointsDeVie; }
    public void setPdv(int pointsDeVie) { this.pointsDeVie = pointsDeVie; }
    public int getSalle() { return numSalle; }
    public void setSalle(int n) { numSalle = n; }
    public int getPdvMax() { return pdvMax; }
    public int getPA() { return ptsAction; }
    public ArrayList<Carte> getDeck() { return deck; }
    public ArrayList<Carte> getPioche() { return pioche; }

    private void soin() {
        pointsDeVie = pdvMax;
    }

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

    // A finir -------------------------------------------------------|
    public void utiliserCarte(Carte c, Monstre cible){
        if(c.getPA() < this.getPA()){
            return;
        }
        for (Carte carte: deck) {
            if(carte.getId() == c.getId())
                deck.remove(carte);
                pioche.add(carte);//pioche
        }
        return;
    }

    private Carte pop() {
        return pioche.remove(0);
    }

    private void addCarteToDeck(){
        if (pioche.size() == 0)
            return;
        deck.add(pop());
    }

    private void initDeck() {
        for (int i = 0; i < 3; i++) {
            addCarteToDeck();
        }
    }
}
