package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Joueur implements Personnage {
    private int numSalle;
    private ObservableList<Carte> deck;
    private int ptsAction;
    private String image;
    Random rand = new Random();
    private Manager leManager = Manager.getInstance();

    // --- Section Carte --- //
    private int soin = 20;
    private int attaque = 10;
    private int poison = 5;
    public int poisonEnCours = 0;
    private int valPoison;
    private Monstre mEmpoisonne;

    public Joueur(String n, int pdv, int pa, int nbDeckCartes) {
        setNom(n);
        setPdvMax(pdv);
        setPointsDeVie(getPdvMax());
        numSalle = 1;
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

    public int getPA() { return ptsAction; }
    public ObservableList<Carte> getDeck() { return deck; }
    public int getNumSalle() {
        return numSalle;
    }
    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    public String getImage(){return image;}

    private void soin() {
        setPointsDeVie(getPdvMax());
    }

    public void renforcer (Bonus b, Salle s) {

        if (this.numSalle == s.getNumSalle() && s.contientBoss()) //Si on tue un boss, bonus améliorés
        {
            switch (b) {
                case Degats:
                    soin += (int)(soin / 1.5);
                    attaque += attaque / 2;
                    poison += poison / 2;
                    break;
                case VieMax:
                    setPdvMax(getPdvMax() + 10); //On augmente le maximum de pdv
                    break;
                case PointAction:
                    ptsAction = ptsAction + 2; //On augmente les ptsAction de 2
                    break;
                case Regeneration:
                    setPointsDeVie(getPointsDeVie() + 5); //On augmente le maximum de pdv
                    soin(); // On remet le joueur au maximum de ses pts de vie
                    break;
            }
        }
        else {
            switch (b) {
                case Degats:
                    soin += soin / 2;
                    attaque += attaque / 2;
                    poison += poison / 2;
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

    public Carte randCarte() {
        int n = rand.nextInt(4);

        if (n == 0)
            return new Carte("Soin", "Soigne vos points de vie", 1, soin, Effets.magique, 1, "images/coeur.png");
        else if (n == 1)
            return new Carte("Poison", "Empoisonne l'ennemi", 3, poison, Effets.duree, 1, "images/poison.png");
        else
            return new Carte("Attaque", "Attaque directe", 1, attaque, Effets.physique, 1, "images/epee.png");
    }

    @Override
    public boolean attaque(Personnage p, int val) {
        Monstre m = (Monstre) p;
        if (poisonEnCours != 0) {
            empoisonne(mEmpoisonne, valPoison);
        }
        m.setPointsDeVie(m.getPointsDeVie() - val);
        System.out.println(val + " Attaque");
        if (m.getPointsDeVie() > 0) {
            Timeline delai = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> attaquePlusTard(m, val))
            );
            delai.play();
        }
        return m.getPointsDeVie() <= 0;
    }

    private void attaquePlusTard(Personnage m, int degats) {
        m.attaque(this, degats);
    }

    public boolean soigne(int valeur, Monstre m) {
        if (poisonEnCours != 0) {
            empoisonne(m, valPoison);
            System.out.println(valPoison + " Soigne");
            if (getPointsDeVie() + valeur <= getPdvMax())
                setPointsDeVie(getPointsDeVie() + valeur);
            else
                setPointsDeVie(getPdvMax());
            return m.getPointsDeVie() <= 0;
        }
        else if (getPointsDeVie() + valeur <= getPdvMax())
            setPointsDeVie(getPointsDeVie() + valeur);
        else
            setPointsDeVie(getPdvMax());

        return false;
    }

    public boolean empoisonnement(Monstre m, Carte c) {
        mEmpoisonne = m;
        valPoison = c.getValeur();

        if (poisonEnCours != 0)
            empoisonne(mEmpoisonne, valPoison);

        poisonEnCours += c.getDelai();
        empoisonne(m, valPoison);

        return mEmpoisonne.getPointsDeVie() <= 0;
    }

    public void empoisonne(Monstre m, int val) {
        m.setPointsDeVie(m.getPointsDeVie() - val);
        System.out.println(val + " Empoisonne");
        poisonEnCours -= 1;
    }
}
