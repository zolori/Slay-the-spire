package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Classe corespondant au personnage incarné par l'utilisateur
 */
public class Joueur implements Personnage {

    private int numSalle;
    private transient ObservableList<Carte> deck;
    private Manager lemanager = Manager.getInstance();
    private String image;
    Random rand = new Random();

    /**
     * Section servant pour les différentes actions au niveau des cartes.
     *      (ex : renforcement, effets de poison...)
     */
    private int soin = 30;
    private int attaque = 15;
    private int poison = 8; // Dégats du poison
    public int poisonEnCours = 0; // Incrémenté en fonction du délai de la carte utilisée.
    private int valPoison; // Dégats du poison sur la durée
    private Monstre mEmpoisonne;

    /**
     * Constructeur joueur.
     *
     * @param n
     *      Nom du joueur.
     * @param pdv
     *      Points de vie du joueur.
     * @param nbDeckCartes
     *      Nombre de carte que le joueur aura dans son deck.
     */
    public Joueur(String n, int pdv, int nbDeckCartes) {
        setNom(n);
        setPdvMax(pdv);
        setPointsDeVie(getPdvMax());
        numSalle = 1;
        deck = FXCollections.observableArrayList();
        initDeck(nbDeckCartes);
        image = "/images/hero.png";
        pdvMaxSer = getPdvMax();
        pdvSer = getPointsDeVie();
        nomSer = n;
    }

    /**
     * Propriété points de vie max.
     */
    private transient IntegerProperty pdvMax= new SimpleIntegerProperty();
        public int getPdvMax(){ return pdvMaxProperty().get();}
        public IntegerProperty pdvMaxProperty() { return pdvMax; }
        public void setPdvMax(int pdvMax) { this.pdvMax.set(pdvMax); }

    /**
     * Point de vie max pour la serialisation.
     */
    private int pdvMaxSer;
    public int getPdvMaxSer() { return pdvMaxSer; }
    public void setPdvMaxSer(int pdvMaxSer) { this.pdvMaxSer = pdvMaxSer; setPdvMax(pdvMaxSer); }

    /**
     * Propriété nom.
     */
    private transient StringProperty nom = new SimpleStringProperty();
        public void setNom(String value) { nomProperty().set(value); }
        public StringProperty nomProperty() { return this.nom; }

    /**
     * Nom pour la serialisation
     */
    private String nomSer;
    public void setNomSer(String nomSer) { this.nomSer = nomSer; setNom(nomSer); }
    public String getNomSer() { return nomSer; }

    /**
     * Propriété point de vie.
     */
    private transient IntegerProperty pdv = new SimpleIntegerProperty();
        public int getPointsDeVie(){return pdvProperty().get();}
        public void setPointsDeVie(int value){ pdvProperty().set(value); pdvSer=getPointsDeVie();}
        public IntegerProperty pdvProperty(){return this.pdv;}

    /**
     * Point de vie pour la serialisation.
     */
    private int pdvSer;
    public int getPdvSer() { return pdvSer; }
    public void setPdvSer(int pdvSer) { this.pdvSer = pdvSer; setPointsDeVie(pdvSer); }

    /**
     * @return le deck.
     */
    public ObservableList<Carte> getDeck() { return deck; }

    /**
     * Met à jour le deck.
     * @param deck
     *      Deck contenant les nouvelles cartes du deck.
     */
    public void setDeck(ArrayList<Carte> deck){
        for(int i=0; i<3; i++){
            this.deck.set(i, deck.get(i));
        }
    }

    /**
     * @return le numéro de la salle où se trouve le joueur.
     */
    public int getNumSalle() {
        return numSalle;
    }

    /**
     * Met à jour la salle où se trouve le joueur.
     *
     * @param numSalle
     *      Nouveau numéro de salle.
     */
    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    /**
     * @return l'image du joueur.
     */
    public String getImage(){return image;}

    /**
     * Remonte les points de vie du joueur au max.
     */
    private void soin() {
        setPointsDeVie(getPdvMax());
    }

    /**
     * renforce les cartes en fonction du bonus choisi lors d'un changement de salle.
     *
     * @param b
     *      Bonus choisi.
     * @param s
     *      Salle terminée.
     */
    public void renforcer (Bonus b, Salle s) {
        if (this.numSalle == s.getNumSalle() && s.contientBoss()) //Si on tue un boss, bonus améliorés
        {
            switch (b) {
                case Degats -> {
                    soin += (int) (soin / 1.5);
                    attaque += attaque / 2;
                    poison += poison / 2;
                }
                case VieMax -> setPdvMax((int) (getPdvMax() * 1.2)); //On augmente le maximum de pdv
                case Regeneration -> {
                    setPdvMax((int) (getPdvMax() * 1.1)); //On augmente le maximum de pdv
                    soin(); // On remet le joueur au maximum de ses pts de vie
                }
            }
        }
        else {
            switch (b) {
                case Degats -> {
                    soin += soin / 2;
                    attaque += attaque / 2;
                    poison += poison / 2;
                }
                case VieMax -> setPdvMax(getPdvMax() + 15 * getNumSalle()); //On augmente le maximum de pdv
                case Regeneration -> soin(); // On remet le joueur au maximum de ses pts de vie
            }
        }
    }

    /**
     * Remplace une carte du deck
     *
     * @param i
     *      Emplacement de la carte à remplacer
     */
    public void remplaceDeckCarte(int i) {
        deck.set(i, randCarte());
    }

    /**
     * Initialise le deck.
     *
     * @param nb_cartes
     *      Nombre de carte à initialiser dans le deck
     */
    public void initDeck(int nb_cartes) {
        for (int i = 0; i < nb_cartes; i++) {
            deck.add(randCarte());
        }
    }

    /**
     * @return une carte aléatoire.
     */
    public Carte randCarte() {
        int n = rand.nextInt(3);

        if (n == 0)
            return new Carte("Soin", "Soigne vos points de vie", 1, soin, "images/coeur.png");
        else if (n == 1)
            return new Carte("Poison", "Empoisonne l'ennemi", 3, poison, "images/poison.png");
        else
            return new Carte("Attaque", "Attaque directe", 1, attaque, "images/epee.png");
    }

    /**
     * Inflige des dégats à un personnage.
     *
     * @param p
     *      Personnage à attaquer.
     * @param val
     *      Montant des dégats.
     *
     * @return
     *      true : si le monstre est vaincu.
     *      false : s'il est toujours en vie.
     */
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

    /**
     * Soigne le joueur.
     *
     * @param valeur
     *      Montant du soin.
     * @param m
     *      Monstre à attaquer si un poison est actif.
     *
     * @return
     *      true : si le monstre est vaincu.
     *      false : s'il est toujours en vie.
     */
    public boolean soigne(int valeur, Monstre m) {
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

    /**
     * Applique un effet d'empoisonnement sur le monstre.
     * Appelle la fonction qui applique le poison.
     *
     * @param m
     *      Monstre à empoissonner.
     * @param c
     *      Carte utilisée pour appliquer le poison.
     *
     * @return
     *      true : si le monstre est vaincu.
     *      false : s'il est toujours en vie.
     */
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

    /**
     * Applique le poison.
     *
     * @param m
     *      Monstre empoisonné.
     * @param val
     *      Montant des dégats.
     */
    public void empoisonne(Monstre m, int val) {
        m.setPointsDeVie(m.getPointsDeVie() - val);
        poisonEnCours -= 1;
    }

    /**
     * Attaque le joueur avec un temps de latence (pour le côté réaliste, oui, on fait ce qu'on peut).
     *
     * @param m
     *      Personnage qui attaque.
     * @param degats
     *      Montant des dégats.
     *
     * @throws IOException
     *      Si erreur dans finPartie, fait remonter une exception.
     */
    private void attaquePlusTard(Personnage m, int degats) throws IOException {
        Monstre monstre = (Monstre) m;
        boolean Alive = monstre.attaque(this, degats);
        if (!Alive){
            lemanager.getPartie().finPartie();
        }
    }

    /**
     * Gère le temps de latence entre l'attaque du joueur et la riposte du monstre.
     *
     * @param m
     *      Monstre qui attaque.
     * @param val
     *      Monstant des dégats.
     */
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

    /**
     * Remet les variables à leur état enregistré par la sauvegarde.
     *
     * @param j
     *      Le joueur.
     */
    public void setAll(Joueur j) {
        setNomSer(j.getNomSer());
        setPdvMaxSer(j.getPdvMaxSer());
        setPdvSer(j.getPdvSer());
    }
}
