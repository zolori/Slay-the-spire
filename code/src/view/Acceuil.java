package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.*;
import java.io.*;

/**
 * Accueil est la classe qui permet de gérer l'affichage et les actions faites sur la vue Accueil
 */
public class Acceuil {
    /**
     * Cette fonction s'éxecute afin de génerer l'affichage de la salle. Elle est appelée par la fonction 'lancer'
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     * @throws IOException
     */
    public void lancementPartie(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Salle.fxml"));
        Parent p = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 1000, 700));
        stage.setFullScreen(true); // Met en plein ecran
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre
    }

    /**
     * Cette fonction s'occupe de lancer une partie.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     */
    public void lancer(ActionEvent actionEvent) {
        Manager leManager = Manager.getInstance();
        leManager.createJoueur("Jean", 300);
        try {
            lancementPartie(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction gère le chargement d'une partie sauvegardée.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Charger une partie'
     * @throws Exception
     */
    public void charger(ActionEvent actionEvent) throws Exception{
        try {
            FileInputStream sauvegarde = new FileInputStream("Partie.ser");
            ObjectInputStream ois = new ObjectInputStream(sauvegarde);

            Manager leManager = Manager.getInstance();
            leManager.deserialiser(ois);

            Partie partie = leManager.getPartie();
            Joueur j = partie.getJoueur();
            Salle s = partie.getSalle(j.getNumSalle());
            s.toString();
            ois.close();

            leManager.createJoueur("Jean", 300);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Salle.fxml"));
            Parent p = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Salle");
            SalleController sc=loader.getController();
            sc.loadPartie(j,s);
            stage.setScene(new Scene(p, 1000, 700));
            stage.setFullScreen(true); // Met en plein ecran
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre

        } catch (IOException e) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Popup.fxml"));
            Parent p = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Retour");
            stage.setScene(new Scene(p, 200, 120));
            stage.show();
        }
    }

    /**
     * Cette fonction gère la suppression d'une sauvegarde.
     */
    public void supprimer() {
        File fichier= new File("Partie.ser");
        fichier.delete();
    }
}
