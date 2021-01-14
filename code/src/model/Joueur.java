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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Joueur implements Personnage,Serializable,SerialisationPartie {
    private int numSalle=0;
    private ObservableList<Carte> deck;
    private Manager lemanager=Manager.getInstance();

    private String image;
    Random rand = new Random();

    // --- Section Carte --- //
    private int soin = 30;
    private int attaque = 15;
    private int poison = 8;
    public int poisonEnCours = 0;
    private int valPoison;
    private Monstre mEmpoisonne;

    public Joueur(String n, int pdv, int nbDeckCartes) {
        setNom(n);
        setPdvMax(pdv);
        setPointsDeVie(getPdvMax());
        numSalle = 1;
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

    public ObservableList<Carte> getDeck() { return deck; }
    public void setDeck(ObservableList<Carte> deck) { this.deck = deck; }
    public void setDeck(ArrayList<Carte> deck){
        for(int i=0; i<3; i++){
            this.deck.set(i, deck.get(i));
        }
    }

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
                    setPdvMax((int) (getPdvMax()*1.2)); //On augmente le maximum de pdv
                    break;
                case Regeneration:
                    setPdvMax((int) (getPdvMax()*1.1)); //On augmente le maximum de pdv
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
                    setPdvMax(getPdvMax() + 15*getNumSalle()); //On augmente le maximum de pdv
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
        attendre(m, m.getDegats());
        return m.getPointsDeVie() <= 0;
    }

    public boolean soigne(int valeur, Monstre m) throws IOException {
        if (poisonEnCours != 0) {
            empoisonne(m, valPoison);
            if (getPointsDeVie() + valeur <= getPdvMax())
                setPointsDeVie(getPointsDeVie() + valeur);
            else
                setPointsDeVie(getPdvMax());
            attendre(m, m.getDegats());
            return m.getPointsDeVie() <= 0;
        }
        else if (getPointsDeVie() + valeur <= getPdvMax()) {
            attendre(m, m.getDegats());
            setPointsDeVie(getPointsDeVie() + valeur);
        }
        else {
            attendre(m, m.getDegats());
            setPointsDeVie(getPdvMax());
        }

        return false;
    }

    public boolean empoisonnement(Monstre m, Carte c) {
        mEmpoisonne = m;
        valPoison = c.getValeur();

        if (poisonEnCours != 0)
            empoisonne(mEmpoisonne, valPoison);

        poisonEnCours += c.getDelai();
        empoisonne(m, valPoison);
        attendre(m, m.getDegats());
        return mEmpoisonne.getPointsDeVie() <= 0;
    }

    public void empoisonne(Monstre m, int val) {
        m.setPointsDeVie(m.getPointsDeVie() - val);
        poisonEnCours -= 1;
    }

    private void attaquePlusTard(Personnage m, int degats) throws IOException {
        Monstre monstre= (Monstre) m;
        boolean Alive = monstre.attaque(this, degats);
        if (!Alive){
            lemanager.getPartie().finPartie();
        }
    }

    private void attendre(Monstre m, int val) {
        if (m.getPointsDeVie() > 0) {
            Timeline delai = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> {
                        try {
                            attaquePlusTard(m, val);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
            );
            delai.play();
        }
    }

    @Override
    public String toString(){
        return getNom() + getPointsDeVie() + getDeck();
    }

    public void serialisation(ObjectOutputStream oos) {
        Manager m = Manager.getInstance();
        Partie p = m.getPartie();
        Joueur j=p.getJoueur();
        String nom =j.getNom();
        int pdv=j.getPointsDeVie();
        List<Carte> deck=j.getDeck().stream().collect(Collectors.toList());

        try {
            oos.writeObject(nom);
            oos.writeObject(pdv);
            oos.writeObject(getPdvMax());
            ArrayList<Carte> deckS= new ArrayList<>(deck);
            for (int i=0; i<deckS.size();i++) {
                Carte c = deckS.get(i);
                c.serialisation(oos);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deserialisation(ObjectInputStream ois) {
        Joueur j=null;
        try {
            this.setNom((String) ois.readObject());
            this.setPointsDeVie((int) ois.readObject());
            this.setPdvMax((int) ois.readObject());
            ArrayList<Carte> deckS= new ArrayList<>();
            for (int i=0; i<3;i++) {
                Carte c=new Carte("Carte","Description",0,0,Effets.physique,0,"image");
                c.deserialisation(ois);
                deckS.add(c);
            }
            this.setDeck(deckS);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
