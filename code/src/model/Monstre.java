package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Monstre affronté par le joueur dans une salle.
 */
public class Monstre implements Personnage {
    /**
     * Numéro de salle où se trouve le monstre.
     */
    private int numSalle;

    /**
     * Dégats brut du monstre.
     */
    private int degats;

    /**
     * Nom du monstre.
     */
    private String nom;

    /**
     * Constructeur de Monstre.
     *
     * @param n
     *      Nom du monstre.
     * @param pdv
     *      Points de vie du monstre.
     * @param num
     *      numéro de salle où se situé le monstre.
     * @param d
     *      Dégats du monstre.
     */
    public Monstre(String n, int pdv, int num, int d) {
        nom = n;
        setPointsDeVie(pdv);
        numSalle = num;
        degats = d;
        image = "/images/Orcs.jpg";
    }

    /**
     * Propriété sur les points de vie du monstre.
     */
    private transient IntegerProperty pointsDeVie= new SimpleIntegerProperty();
        public int getPointsDeVie() { return pointsDeVie.get(); }
        public IntegerProperty pointsDeVieProperty() { return pointsDeVie; }
        public void setPointsDeVie(int pointsDeVie) { this.pointsDeVie.set(pointsDeVie); pdv=getPointsDeVie();}

    /**
     * Points de vie pour la serialisation (qui a besoin d'un int, pas IntegerProperty)
     */
    private int pdv;

    /**
     * @return les points de vie du monstre.
     */
    public int getPdv() { return pdv; }

    /**
     * Image du monstre.
     */
    private String image;

    /**
     * @return le numéro de salle.
     */
    public int getSalle() {
        return numSalle;
    }

    /**
     * Les dégats du monstre.
     * @return
     */
    public int getDegats() { return degats; }

    /**
     * @return le nom du monstre.
     */
    public  String getNom() {
        return nom;
    }

    /**
     * @return l'image du monstre.
     */
    public String getImage() { return image; }

    /**
     * Met à jour le numéro de salle.
     *
     * @param numSalle
     *      Nouveau numéro de salle.
     */
    public void setNumSalle(int numSalle) { this.numSalle = numSalle; }

    /**
     * Met à jour les dégats.
     *
     * @param degats
     *      Nouveaux dégats du monstre.
     */
    public void setDegats(int degats) { this.degats = degats; }

    /**
     * Met à jour le nom du monstre.
     *
     * @param nom
     *      Nouveau nom.
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * @return
     *      true : le monstre est un boss.
     *      false : ce n'en est pas un.
     */
    protected boolean isBoss() {
        return this.getClass().getName() == "Boss";
    }

    /**
     * Attaque le personnage passé en paramètre.
     *
     * @param p
     *      Personnage à attaquer.
     * @param val
     *      Montant des dégats.
     *
     * @return
     *      true : le joueur est toujours en vie.
     *      false : il est mort.
     */
    @Override
    public boolean attaque(Personnage p, int val) {
        p.setPointsDeVie(p.getPointsDeVie() - degats);
        return p.getPointsDeVie() > 0;
    }

    /**
     * Remet les variables à leur état enregistré par la sauvegarde.
     *
     * @param m
     *      le monstre.
     */
    public void setAll(Monstre m) {
        setNom(m.getNom());
        setPointsDeVie(m.getPdv());
        setNumSalle(m.getSalle());
        setDegats(m.getDegats());
    }
}
