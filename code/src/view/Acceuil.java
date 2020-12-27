package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Joueur;
import model.Manager;
import model.Partie;

import java.io.IOException;

public class Acceuil {
    @FXML
    private Button lancer;
    @FXML
    private Button charger;
    @FXML
    private Button supprimer;

    public void lancer(ActionEvent actionEvent) throws IOException {
        Manager leManager = Manager.getInstance();
        Joueur joueur = leManager.createJoueur("Jean", 300, 1);
        Partie partie = new Partie(joueur);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Salle.fxml"));
        Parent p = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 1000, 700));
        // stage.setFullScreen(true); // Met en plein ecran
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre
    }
    public void charger(ActionEvent actionEvent) {
        charger.setText("Chargement de la partie !");
    }
    public void supprimer(ActionEvent actionEvent) {
        supprimer.setText("Supression de la partie !");
    }

}
