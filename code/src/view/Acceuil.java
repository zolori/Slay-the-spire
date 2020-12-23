package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;

public class Acceuil /*extends Application implements Initializable*/ {
    @FXML
    private Button lancer;
    @FXML
    private Button charger;
    @FXML
    private Button supprimer;

    public void lancer(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        stage.setFullScreen(true); // Met en plein ecran
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //Cache la 1er fenetre
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
