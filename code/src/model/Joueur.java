package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;

public class Joueur {
    private int numSalle;
    private ObservableList<Carte> deck;
    private int ptsAction;
    private int pointsDeVie;
    private int pdvMax;
    Random rand = new Random();

    public Joueur(String n, int pdv, int pa, int nbDeckCartes) {
        setNom(n);
        pdvMax = pdv;
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = FXCollections.observableArrayList();
        initDeck(nbDeckCartes);
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
    public ObservableList<Carte> getDeck() { return deck; }

    private void soin() {
        pointsDeVie = pdvMax;
    }

    public void renforcer (Bonus b, Salle s) {
        //On récupère la pioche
        Boolean isBoss = false;

        //On regarde si il y avait un boss dans la salle
        if (this.numSalle == s.getNumSalle() && s.contientBoss()) //Si on tue un boss, bonus améliorés
        {
            isBoss = true; //Si il y a un boss, on passe la variable a true
            switch (b) {
                case Degats:
//                    for ( Carte c : pioche ) {
//                        deck = c.renforcement(deck.toArray(), isBoss); //On utilise le renforcement de manière amélioré
//                    }
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
//                    for ( Carte c : pioche ) {
//                        deck = c.renforcement(deck, isBoss); //On utilise le renforcement de manière amélioré
//                    }
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

    public void remplaceDeckCarte(int i) {
        deck.set(i, randCarte());
    }

    public void initDeck(int nb_cartes) {
        for (int i = 0; i < nb_cartes; i++) {
            deck.add(randCarte());
        }
    }

    private Carte randCarte() {
        int n = rand.nextInt(3);

        if (n == 0)
            return new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png");
        else if (n == 1)
            return new Carte("Protection", "Description carte", 1, 30, Effets.physique, 1, "images/bouclier.png");
        else
            return new Carte("Soin", "Description carte", 1, 40, Effets.physique, 1, "images/coeur.png");
    }

}
