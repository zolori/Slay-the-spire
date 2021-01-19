package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

/**
 * Partie dans laquelle se déroule le jeu.
 */
public class Partie {
    /**
     * liste de salle que contient la partie.
     */
    private ArrayList<Salle> salles = new ArrayList<>();

    /**
     * Nombre de salle
     */
    private final int NBSALLE = 21;

    /**
     * Joueur utilisé par l'utilisateur.
     */
    private Joueur joueur;

    /**
     * Points de vie de base pour la création des monstres.
     */
    private int pdv = 66;

    /**
     * Dégats de base pour la création des monstres.
     */
    private int degats = 6;
    private Salle salleactuelle;

    /**
     * Constructeur de Partie.
     *
     * @param j
     *      Joueur utilisé dans la partie par l'utilisateur.
     */
    public Partie(Joueur j) {
        joueur = j;
        joueur.setNumSalle(1);
        salles = new ArrayList<>(NBSALLE-1);

        for (int i = 1; i < NBSALLE; i++) {
            Salle s = new Salle(i);
            if(i%5!=0) {
                pdv = (int) (pdv * 1.5);
                degats = (int) (degats * 1.5);
                Monstre m = new Monstre("Monstre", pdv, i, degats);
                s.setMonstre(m);

            }else{
                pdv = pdv * 2;
                degats = degats * 2;
                Monstre b = new Boss("Boss",pdv,i,degats);
                s.setMonstre(b);
            }
            salles.add(s);
        }
        salleactuelle=getSalle(joueur.getNumSalle());
    }

    /**
     * 2e Constructeur de Partie.
     * Utilisé lors de la récupération des données d'una partie sauvegardée.
     *
     * @param j
     *      Joueur sauvegardé.
     * @param deck
     *      Deck sauvegardé.
     * @param s
     *      Salle sauvegardée.
     * @param m
     *      Monstre sauvegardé.
     */
    public Partie(Joueur j, ArrayList<Carte> deck, Salle s, Monstre m) {
        this(j);
        joueur = j;
        j.setDeck(deck);
        s.setMonstre(m);
        s.toString();
        salles.remove(s.getNumSalle()-1);
        salles.add(s.getNumSalle()-1,s);
    }

    /**
     * @param num
     *      Numéro de salle où se trouve le joueur.
     * @return le numéro de la salle actuelle.
     */
    public Salle getSalle(int num) {
        return salles.get(num-1);
    }

    /**
     * @return le joueur.
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Met à jour le joueur.
     *
     * @param joueur
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Gère la fin de la partie.
     *
     * @throws IOException
     *      si load échoue, renvoie exception.
     */
    public void finPartie() throws IOException {
        File fichier = new File("Partie.ser");
        fichier.delete();

        Parent p = FXMLLoader.load(getClass().getResource("/Defaite.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Defaite");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        stage.setFullScreen(true);
    }

    public void setSalle(Salle s) { salles.add(s); }
}
