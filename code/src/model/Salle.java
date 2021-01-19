package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

/**
 * Salle ou se trouve les monstres.
 */
public class Salle {
    /**
     * Numéro de la salle.
     */
    private int numSalle;

    /**
     *
     */
    private Monstre monstre;

    /**
     * Constructeur de Salle.
     *
     * @param numeroSalle
     *      numéro de salle.
     */
    public Salle(int numeroSalle) {
        numSalle = numeroSalle;
    }

    /**
     * @return le monstre que contient la salle.
     */
    public Monstre getMonstre() {
        return monstre;
    }

    /**
     * Met à jour le monstre de la salle.
     *
     * @param m
     *      Nouveau monstre.
     */
    public void setMonstre(Monstre m){
        monstre = m;
    }

    /**
     * @return le numéro de salle.
     */
    public int getNumSalle() {
        return numSalle;
    }

    /**
     * @return
     *      true si la salle contient un boss.
     *      false si non.
     */
    public boolean contientBoss() {
        return monstre.isBoss();
    }

    /**
     * Fait apparaitre la fenetre des bonus.
     *
     * @throws IOException
     *      renvoie une exception si load fait nimp
     */
    public void changerSalle() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Bonus.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Bonus");
        stage.setScene(new Scene(p, 600, 250));
        stage.show();
    }

    @Override
    public String toString() {
        return "Salle{" +
                "numSalle=" + numSalle +
                ", monstre=" + monstre +
                '}';
    }
}
