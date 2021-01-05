package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.*;

import java.io.*;

public class Acceuil {
    @FXML
    private Button lancer;
    @FXML
    private Button charger;
    @FXML
    private Button supprimer;

    public void lancementPartie(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Salle.fxml"));
        Parent p = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 1000, 700));
        // stage.setFullScreen(true); // Met en plein ecran
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre
    }

    public void lancer(ActionEvent actionEvent) {
        Manager leManager = Manager.getInstance();
        Joueur joueur = leManager.createJoueur("Jean", 300);
        try {
            lancementPartie(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void charger(ActionEvent actionEvent){
        try {
                FileInputStream sauvegarde = new FileInputStream("Partie.ser");
                ObjectInputStream ois = new ObjectInputStream(sauvegarde);

                Joueur j = new Joueur("Sauvegarde", 100, 3);
                j.deserialisation(ois);
                Salle s = new Salle(1);
                s.deserialisation(ois);
                Monstre m = new Monstre("Sauvegarde", 100, 1, 15);
                m.deserialisation(ois);
                s.setMonstre(m);
                ois.close();


            Manager leManager = Manager.getInstance();
            Joueur joueur = leManager.createJoueur("Jean", 300);

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
            //e.printStackTrace();
            System.out.println("Pas de Sauvegarde");
        }
    }

    public void supprimer(ActionEvent actionEvent) {
        File fichier= new File("Partie.ser");
        fichier.delete();
    }

}
