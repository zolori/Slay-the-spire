package view;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import model.Joueur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Acceuil /*extends Application implements Initializable*/ {
    @FXML
    private Button lancer;
    @FXML
    private Button charger;
    @FXML
    private Button supprimer;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox hbox;
    @FXML
    private Text texte;

    private Joueur joueur;

    public void lancer(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre
        stage.setFullScreen(true); // Met en plein ecran

    }
    public void charger(ActionEvent actionEvent) {
        charger.setText("Chargement de la partie !");
    }
    public void supprimer(ActionEvent actionEvent) {
        supprimer.setText("Supression de la partie !");
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        texte.setText(joueur.getNom());
//    }
}
