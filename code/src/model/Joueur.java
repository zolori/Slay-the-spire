package model;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Random;

public class Joueur implements Actions {
    private int numSalle;
    private ObservableList<Carte> deck;
    private int ptsAction;
    private String image;
    Random rand = new Random();
    private Manager leManager = Manager.getInstance();

    public Joueur(String n, int pdv, int pa, int nbDeckCartes) {
        setNom(n);
        setPdvMax(pdv);
        setPointsDeVie(getPdvMax());
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = FXCollections.observableArrayList();
        initDeck(nbDeckCartes);
        image="/images/hero.png";
    }

    private IntegerProperty pdvMax= new SimpleIntegerProperty();
    public int getPdvMax(){return pdvMaxProperty().get();}
    public IntegerProperty pdvMaxProperty() { return pdvMax; }
    public void setPdvMax(int pdvMax) { this.pdvMax.set(pdvMax); }

    private StringProperty nom = new SimpleStringProperty();
    public String getNom() { return nomProperty().get(); }
    public void setNom(String value) { nomProperty().set(value); }
    public StringProperty nomProperty() { return this.nom; }

    private IntegerProperty pdv = new SimpleIntegerProperty();
    public int getPointsDeVie(){return pdvProperty().get();}
    public void setPointsDeVie(int value){ pdvProperty().set(value);}
    public IntegerProperty pdvProperty(){return this.pdv;}


    public int getSalle() { return numSalle; }
    public void setSalle(int n) { numSalle = n; }

    public int getPA() { return ptsAction; }
    public ObservableList<Carte> getDeck() { return deck; }


    public String getImage(){return image;}

    private void soin() {
        setPointsDeVie(getPdvMax());
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
                    setPdvMax(getPdvMax()+10); //On augmente le maximum de pdv
                    break;
                case PointAction:
                    ptsAction=ptsAction+2; //On augmente les ptsAction de 2
                    break;
                case Regeneration:
                    setPointsDeVie(getPointsDeVie()+5); //On augmente le maximum de pdv
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
                    setPdvMax(getPdvMax() + 5); //On augmente le maximum de pdv
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
        int n = rand.nextInt(4);

        if (n == 0)
            return new Carte("Soin", "Description carte", 1, 20, Effets.physique, 1, "images/coeur.png");
//        else if (n == 1)
//            return new Carte("Protection", "Description carte", 1, 30, Effets.physique, 1, "images/bouclier.png");
        else
            return new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png");
    }

    public void attaque(int val) {
        Monstre m = leManager.getSalle().getMonstre();
        m.subit(val);
        if (m.getPointsDeVie() > 0) {
            m.attaque(val);
            if (leManager.getJoueur().getPointsDeVie() <= 0)
                leManager.getPartie().finPartie();
        }
    }

    public void soigne(int valeur) {
        if (getPointsDeVie()+valeur<getPdvMax())
            setPointsDeVie(getPointsDeVie()+valeur);
        else
            setPointsDeVie(getPdvMax());
    }
}
