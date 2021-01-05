package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Joueur;
import model.Manager;

import java.io.IOException;

public class Defaite {
    public void Restart(ActionEvent actionEvent) throws IOException {
        Manager leManager = Manager.getInstance();
        Joueur joueur = leManager.createJoueur("Jean", 300);
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        stage.setFullScreen(true);
    }
}
