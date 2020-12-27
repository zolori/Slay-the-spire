package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import model.Joueur;
import model.Manager;

public class BonusController /*implements Initializable*/{
    @FXML
    private BorderPane borderpane = new BorderPane();
    @FXML
    private HBox hbox = new HBox();

    public void initialize() {

    }

    public void VieMax(ActionEvent actionEvent) throws IOException {
        // Regarder pour la méthode renforcer (Joueur). Passer bonus en paramètres ? -> bonus = enum != classe
        retourAccueil(actionEvent);
    }

    public void Regen(ActionEvent actionEvent) throws IOException{
        // Regarder pour la méthode renforcer (Joueur). Passer bonus en paramètres ? -> bonus = enum != classe
        retourAccueil(actionEvent);
    }

    public void PA(ActionEvent actionEvent) throws IOException{
        // Regarder pour la méthode renforcer (Joueur). Passer bonus en paramètres ? -> bonus = enum != classe
        retourAccueil(actionEvent);
    }

    public void Degats(ActionEvent actionEvent) throws IOException{
        // Regarder pour la méthode renforcer (Joueur). Passer bonus en paramètres ? -> bonus = enum != classe
        retourAccueil(actionEvent);
    }

    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 1000, 700));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        // stage.setFullScreen(true);
    }
}
